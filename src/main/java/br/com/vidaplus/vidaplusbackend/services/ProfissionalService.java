package br.com.vidaplus.vidaplusbackend.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vidaplus.vidaplusbackend.dto.*;
import br.com.vidaplus.vidaplusbackend.entities.*;
import br.com.vidaplus.vidaplusbackend.enums.StatusConsulta;
import br.com.vidaplus.vidaplusbackend.enums.TipoConsulta;
import br.com.vidaplus.vidaplusbackend.exceptions.ResourceNotFoundException;
import br.com.vidaplus.vidaplusbackend.mappers.ProfissionalMapper;
import br.com.vidaplus.vidaplusbackend.mappers.ProntuarioMapper;
import br.com.vidaplus.vidaplusbackend.repositories.*;

@Service
public class ProfissionalService {

	private final ProfissionalRepository profissionalRepository;
	private final ConsultaRepository consultaRepository;
	private final ProntuarioRepository prontuarioRepository;
	private final UserRepository userRepository;
	private final PacienteRepository pacienteRepository;
	private final EncryptionService encryptionService;
	private final ProntuarioMapper prontuarioMapper;

	public ProfissionalService(ProfissionalRepository profissionalRepository, ConsultaRepository consultaRepository,
			ProntuarioRepository prontuarioRepository, UserRepository userRepository,
			PacienteRepository pacienteRepository, EncryptionService encryptionService,
			ProntuarioMapper prontuarioMapper) {

		this.profissionalRepository = profissionalRepository;
		this.consultaRepository = consultaRepository;
		this.prontuarioRepository = prontuarioRepository;
		this.userRepository = userRepository;
		this.pacienteRepository = pacienteRepository;
		this.encryptionService = encryptionService;
		this.prontuarioMapper = prontuarioMapper;
	}

	// CRUD
	@Transactional(readOnly = true)
	public List<ProfissionalDTO> findAllAtivos() {
		return profissionalRepository.findAll().stream().filter(Profissional::getAtivo).map(ProfissionalMapper::toDTO)
				.toList();
	}

	@Transactional(readOnly = true)
	public ProfissionalDTO getById(Long id) {
		return ProfissionalMapper.toDTO(validarProfissional(id));
	}

	@Transactional
	public ProfissionalDTO create(ProfissionalDTO dto) {
		Profissional profissional = ProfissionalMapper.toEntity(dto);
		profissional.setAtivo(true);
		return ProfissionalMapper.toDTO(profissionalRepository.save(profissional));
	}

	@Transactional
	public ProfissionalDTO update(Long id, ProfissionalDTO dto) {
		Profissional profissional = validarProfissional(id);

		if (dto.getNome() != null)
			profissional.setNome(dto.getNome());
		if (dto.getEmail() != null)
			profissional.setEmail(dto.getEmail());
		if (dto.getEspecialidade() != null)
			profissional.setEspecialidade(dto.getEspecialidade());
		if (dto.getCrm() != null)
			profissional.setRegistroProfissional(dto.getCrm());
		if (dto.getAtivo() != null)
			profissional.setAtivo(dto.getAtivo());

		return ProfissionalMapper.toDTO(profissionalRepository.save(profissional));
	}

	@Transactional
	public void delete(Long id) {
		Profissional profissional = validarProfissional(id);
		profissional.setAtivo(false);
		profissionalRepository.save(profissional);
	}

// AGENDA
	@Transactional(readOnly = true)
	public List<AgendaDTO> getAgendaDoUser(String username) {
		Profissional profissional = buscarProfissionalPorUsername(username);
		return consultaRepository.findByProfissionalId(profissional.getId()).stream().map(this::toAgendaDTO).toList();
	}

	private AgendaDTO toAgendaDTO(Consulta consulta) {
		return new AgendaDTO(consulta.getId(), consulta.getPaciente().getId(), consulta.getPaciente().getNome(),
				consulta.getDataHora(), consulta.getStatus().name(), consulta.isTeleconsulta());
	}

	// AGENDAR CONSULTA PELO PROFISSIONAL
	@Transactional
	public ConsultaResponseDTO agendarConsulta(String username, ConsultaRequestDTO dto) {
		Profissional profissional = buscarProfissionalPorUsername(username);

		Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
				.orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

		Consulta consulta = new Consulta();
		consulta.setProfissional(profissional);
		consulta.setPaciente(paciente);

		try {
			consulta.setDataHora(LocalDateTime.parse(dto.getDataHora()));
		} catch (Exception e) {
			throw new IllegalArgumentException("Data/hora inválida. Use o formato ISO 8601: yyyy-MM-ddTHH:mm:ss");
		}

		if (dto.getTipo() != null && !dto.getTipo().isBlank()) {
			try {
				consulta.setTipo(TipoConsulta.valueOf(dto.getTipo().toUpperCase()));
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Tipo de consulta inválido");
			}
		}

		consulta.setMotivo(dto.getMotivo());
		consulta.setStatus(StatusConsulta.AGENDADA);

		consultaRepository.save(consulta);

		return new ConsultaResponseDTO(consulta.getId(), paciente.getId(), paciente.getNome(), profissional.getId(),
				profissional.getNome(), consulta.getUnidade() != null ? consulta.getUnidade().getId() : null,
				consulta.getUnidade() != null ? consulta.getUnidade().getNome() : null,
				consulta.getDataHora().toString(), // converte LocalDateTime para String ISO
				consulta.isTeleconsulta() ? "TELECONSULTA" : "PRESENCIAL", consulta.getStatus(), consulta.getMotivo(),
				consulta.isTeleconsulta() ? consulta.getLinkTeleconsulta() : null);
	}

// CANCELAR CONSULTA PELO PROFISSIONAL
	@Transactional
	public void cancelarConsulta(String username, Long consultaId) {
		Profissional profissional = buscarProfissionalPorUsername(username);

		Consulta consulta = consultaRepository.findById(consultaId)
				.orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));

		if (!consulta.getProfissional().getId().equals(profissional.getId())) {
			throw new ResourceNotFoundException("Acesso negado: consulta não pertence ao profissional logado");
		}

		consulta.setStatus(StatusConsulta.CANCELADA);
		consultaRepository.save(consulta);
	}

// PRONTUÁRIO, HISTÓRICO, RECEITA
	@Transactional
	public ProntuarioResponseDTO atualizarProntuarioPorUser(String username, Long prontuarioId,
			ProntuarioRequestDTO dto) {

		Profissional profissional = buscarProfissionalPorUsername(username);

		Prontuario prontuario = prontuarioRepository.findById(prontuarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Prontuário não encontrado"));

		if (prontuario.getConsulta() != null && prontuario.getConsulta().getProfissional() != null) {
			if (!prontuario.getConsulta().getProfissional().getId().equals(profissional.getId())) {
				throw new ResourceNotFoundException("Acesso negado ao prontuário");
			}
		} else if (prontuario.getProfissional() != null) {
			if (!prontuario.getProfissional().getId().equals(profissional.getId())) {
				throw new ResourceNotFoundException("Acesso negado ao prontuário");
			}
		} else {
			throw new ResourceNotFoundException("Prontuário sem profissional vinculado");
		}

		dto.applyToEntity(prontuario, encryptionService);
		Prontuario salvo = prontuarioRepository.saveAndFlush(prontuario);
		return toProntuarioResponseDTO(salvo);
	}

	@Transactional(readOnly = true)
	public List<ProntuarioResponseDTO> getHistoricoPacientePorUser(String username, Long pacienteId) {
		buscarProfissionalPorUsername(username);
		return prontuarioRepository.findByPacienteIdAndDeletedAtIsNullOrderByCreatedAtDesc(pacienteId).stream()
				.map(this::toProntuarioResponseDTO).toList();
	}

	@Transactional
	public ReceitaDigitalDTO emitirReceitaPorUser(String username, ReceitaDigitalDTO dto) {
		buscarProfissionalPorUsername(username);
		return dto;
	}

	private ProntuarioResponseDTO toProntuarioResponseDTO(Prontuario p) {
		String descricao = encryptionService.decryptMySQL(p.getDescricaoEnc());
		String diagnostico = encryptionService.decryptMySQL(p.getDiagnosticoEnc());
		String observacoes = encryptionService.decryptMySQL(p.getObservacoesEnc());
		return prontuarioMapper.toDTO(p, descricao, diagnostico, observacoes);
	}

// AUXILIARES
	private Profissional buscarProfissionalPorUsername(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
		if (user.getProfissional() == null) {
			throw new ResourceNotFoundException("Usuário não está vinculado a um profissional");
		}
		return user.getProfissional();
	}

	private Profissional validarProfissional(Long id) {
		return profissionalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado com id: " + id));
	}

	@Transactional(readOnly = true)
	public ProfissionalDTO getProfissionalLogado(String username) {
		Profissional profissional = buscarProfissionalPorUsername(username);
		if (!Boolean.TRUE.equals(profissional.getAtivo())) {
			throw new ResourceNotFoundException("Profissional inativo");
		}
		return ProfissionalMapper.toDTO(profissional);
	}
}
