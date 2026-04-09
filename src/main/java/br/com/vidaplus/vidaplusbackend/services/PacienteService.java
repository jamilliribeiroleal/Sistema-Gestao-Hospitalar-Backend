package br.com.vidaplus.vidaplusbackend.services;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vidaplus.vidaplusbackend.dto.ConsultaResponseDTO;
import br.com.vidaplus.vidaplusbackend.dto.PacienteCadastroDTO;
import br.com.vidaplus.vidaplusbackend.dto.PacienteDTO;
import br.com.vidaplus.vidaplusbackend.dto.ProntuarioResponseDTO;
import br.com.vidaplus.vidaplusbackend.entities.Paciente;
import br.com.vidaplus.vidaplusbackend.entities.Prontuario;
import br.com.vidaplus.vidaplusbackend.entities.Role;
import br.com.vidaplus.vidaplusbackend.entities.User;
import br.com.vidaplus.vidaplusbackend.enums.PacienteSexo;
import br.com.vidaplus.vidaplusbackend.exceptions.ResourceNotFoundException;
import br.com.vidaplus.vidaplusbackend.mappers.ProntuarioMapper;
import br.com.vidaplus.vidaplusbackend.repositories.PacienteRepository;
import br.com.vidaplus.vidaplusbackend.repositories.ProntuarioRepository;
import br.com.vidaplus.vidaplusbackend.repositories.RoleRepository;
import br.com.vidaplus.vidaplusbackend.repositories.UserRepository;

@Service
public class PacienteService {

	private final PacienteRepository pacienteRepository;
	private final UserRepository userRepository;
	private final ProntuarioRepository prontuarioRepository;
	private final EncryptionService encryptionService;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;
	private final ProntuarioMapper prontuarioMapper;

	public PacienteService(PacienteRepository pacienteRepository, UserRepository userRepository,
			ProntuarioRepository prontuarioRepository, EncryptionService encryptionService,
			PasswordEncoder passwordEncoder, RoleRepository roleRepository, ProntuarioMapper prontuarioMapper) {

		this.pacienteRepository = pacienteRepository;
		this.userRepository = userRepository;
		this.prontuarioRepository = prontuarioRepository;
		this.encryptionService = encryptionService;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
		this.prontuarioMapper = prontuarioMapper;
	}

//  REGISTRO PACIENTE + USER
	@Transactional
	public User registrarPaciente(PacienteCadastroDTO dto) {

		if (dto.getPassword() == null || dto.getPassword().isBlank()) {
			throw new IllegalArgumentException("Senha é obrigatória");
		}

		if (userRepository.existsByEmail(dto.getEmail())) {
			throw new IllegalArgumentException("Email já está em uso");
		}

		String username = (dto.getUsername() != null && !dto.getUsername().isBlank()) ? dto.getUsername()
				: dto.getEmail();

		if (userRepository.existsByUsername(username)) {
			throw new IllegalArgumentException("Username já está em uso");
		}

		Role rolePaciente = roleRepository.findByName("ROLE_PACIENTE")
				.orElseThrow(() -> new IllegalStateException("Role ROLE_PACIENTE não encontrada"));

		// USER
		User user = new User();
		user.setUsername(username);
		user.setEmail(dto.getEmail());
		user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
		user.setActive(true);
		user.setRoles(Set.of(rolePaciente));

		// PACIENTE
		Paciente paciente = new Paciente();
		paciente.setNome(dto.getNome());
		paciente.setDataNascimento(dto.getDataNascimento());
		paciente.setEmail(dto.getEmail());
		paciente.setAtivo(true);

		if (dto.getSexo() == null || dto.getSexo().isBlank()) {
			throw new IllegalArgumentException("Sexo é obrigatório");
		}

		try {
			paciente.setSexo(PacienteSexo.valueOf(dto.getSexo()));
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Sexo inválido. Valores permitidos: M, F, O");
		}

		paciente.setSexo(PacienteSexo.from(dto.getSexo()));

		if (dto.getCpf() != null && !dto.getCpf().isBlank()) {
			paciente.setCpfEnc(encryptionService.encryptMySQL(dto.getCpf()));
			paciente.setCpfHash(encryptionService.hash(dto.getCpf()));
		}

		if (dto.getTelefone() != null && !dto.getTelefone().isBlank()) {
			paciente.setTelefoneEnc(encryptionService.encryptMySQL(dto.getTelefone()));
		}

		if (dto.getEndereco() != null && !dto.getEndereco().isBlank()) {
			paciente.setEnderecoEnc(encryptionService.encryptMySQL(dto.getEndereco()));
		}

		paciente.setUser(user);
		user.setPaciente(paciente);
		userRepository.save(user);

		return user;
	}

	// PACIENTE LOGADO
	@Transactional(readOnly = true)
	public PacienteDTO getPacienteLogado(String username) {

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

		Paciente paciente = user.getPaciente();

		if (paciente == null) {
			throw new IllegalStateException("Usuário não está vinculado a um paciente");
		}

		if (!Boolean.TRUE.equals(paciente.getAtivo())) {
			throw new IllegalStateException("Paciente inativo");
		}

		return toDTO(paciente);
	}

	// HISTÓRICO CLÍNICO
	@Transactional(readOnly = true)
	public List<ProntuarioResponseDTO> getHistoricoClinico(Long pacienteId) {

		return prontuarioRepository.findByPacienteIdAndDeletedAtIsNullOrderByCreatedAtDesc(pacienteId).stream()
				.map(this::toProntuarioResponseDTO).collect(Collectors.toList());
	}

	private ProntuarioResponseDTO toProntuarioResponseDTO(Prontuario p) {

		return prontuarioMapper.toDTO(p, encryptionService.decryptMySQL(p.getDescricaoEnc()),
				encryptionService.decryptMySQL(p.getDiagnosticoEnc()),
				encryptionService.decryptMySQL(p.getObservacoesEnc()));
	}

//  CONSULTAS
	@Transactional(readOnly = true)
	public List<ConsultaResponseDTO> getConsultasDoPaciente(Long pacienteId) {

		Paciente paciente = findById(pacienteId);

		return paciente.getConsultas().stream().map(c -> {
			ConsultaResponseDTO dto = new ConsultaResponseDTO();
			dto.setId(c.getId());
			dto.setPacienteId(paciente.getId());
			dto.setPacienteNome(paciente.getNome());

			if (c.getProfissional() != null) {
				dto.setProfissionalId(c.getProfissional().getId());
				dto.setProfissionalNome(c.getProfissional().getNome());
			}

			dto.setDataHora(
					c.getDataHora() != null ? c.getDataHora().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null);

			dto.setStatus(c.getStatus());
			dto.setTipo(c.getTipo() != null ? c.getTipo().name() : null);
			dto.setMotivo(c.getMotivo());
			dto.setLinkTeleconsulta(c.getLinkTeleconsulta());

			return dto;
		}).collect(Collectors.toList());
	}

//  AUXILIARES
	private Paciente findById(Long id) {
		return pacienteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + id));
	}

	private PacienteDTO toDTO(Paciente p) {
		return new PacienteDTO(p.getId(), p.getNome(), p.getDataNascimento(), p.getSexo(), p.getEmail(), p.getAtivo());
	}
}
