package br.com.vidaplus.vidaplusbackend.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vidaplus.vidaplusbackend.dto.*;
import br.com.vidaplus.vidaplusbackend.entities.*;
import br.com.vidaplus.vidaplusbackend.enums.PacienteSexo;
import br.com.vidaplus.vidaplusbackend.enums.StatusInternacao;
import br.com.vidaplus.vidaplusbackend.exceptions.ResourceNotFoundException;
import br.com.vidaplus.vidaplusbackend.repositories.*;

@Service
public class AdministradorService {

	private final PacienteRepository pacienteRepository;
	private final ProfissionalRepository profissionalRepository;
	private final InternacaoRepository internacaoRepository;
	private final UnidadeRepository unidadeRepository;
	private final EncryptionService encryptionService;

	public AdministradorService(PacienteRepository pacienteRepository, ProfissionalRepository profissionalRepository,
			InternacaoRepository internacaoRepository, UnidadeRepository unidadeRepository,
			EncryptionService encryptionService) {

		this.pacienteRepository = pacienteRepository;
		this.profissionalRepository = profissionalRepository;
		this.internacaoRepository = internacaoRepository;
		this.unidadeRepository = unidadeRepository;
		this.encryptionService = encryptionService;
	}

	// PACIENTES

	@Transactional(readOnly = true)
	public List<PacienteAdminDTO> listarPacientesDTO() {
		return pacienteRepository.findAll().stream().map(this::toPacienteAdminDTO).toList();
	}

	@Transactional(readOnly = true)
	public PacienteAdminDTO buscarPacientePorId(Long pacienteId) {
		Paciente paciente = pacienteRepository.findById(pacienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + pacienteId));

		return toPacienteAdminDTO(paciente);
	}

	@Transactional
	public PacienteAdminDTO criarPacienteAdminDTO(PacienteCreateDTO dto) {

		Paciente paciente = new Paciente();
		paciente.setNome(dto.getNome());
		paciente.setDataNascimento(dto.getDataNascimento());
		paciente.setSexo(PacienteSexo.valueOf(dto.getSexo()));
		paciente.setEmail(dto.getEmail());
		paciente.setAtivo(true);

		if (dto.getTelefone() != null && !dto.getTelefone().isBlank()) {
			paciente.setTelefoneEnc(encryptionService.encryptMySQL(dto.getTelefone()));
		}

		pacienteRepository.save(paciente);
		return toPacienteAdminDTO(paciente);
	}

	@Transactional
	public PacienteAdminDTO atualizarPacienteAdminDTO(Long pacienteId, PacienteUpdateDTO dto) {

		Paciente paciente = pacienteRepository.findById(pacienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

		if (dto.getNome() != null)
			paciente.setNome(dto.getNome());
		if (dto.getSexo() != null)
			paciente.setSexo(PacienteSexo.valueOf(dto.getSexo()));
		if (dto.getEmail() != null)
			paciente.setEmail(dto.getEmail());

		if (dto.getTelefone() != null && !dto.getTelefone().isBlank()) {
			paciente.setTelefoneEnc(encryptionService.encryptMySQL(dto.getTelefone()));
		}

		pacienteRepository.save(paciente);
		return toPacienteAdminDTO(paciente);
	}

	@Transactional
	public void deletarPaciente(Long pacienteId) {
		Paciente paciente = pacienteRepository.findById(pacienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

		paciente.setAtivo(false);
		pacienteRepository.save(paciente);
	}

	private PacienteAdminDTO toPacienteAdminDTO(Paciente paciente) {
		return new PacienteAdminDTO(paciente.getId(), paciente.getNome(), paciente.getAtivo());
	}

//  PROFISSIONAIS

	@Transactional(readOnly = true)
	public List<ProfissionalAdminDTO> listarProfissionaisDTO() {
		return profissionalRepository.findAll().stream().map(this::toProfissionalAdminDTO).toList();
	}

	@Transactional(readOnly = true)
	public ProfissionalAdminDTO buscarProfissionalPorId(Long profissionalId) {

		Profissional profissional = profissionalRepository.findById(profissionalId).orElseThrow(
				() -> new ResourceNotFoundException("Profissional não encontrado com id: " + profissionalId));

		return toProfissionalAdminDTO(profissional);
	}

	@Transactional
	public ProfissionalAdminDTO criarProfissionalAdminDTO(ProfissionalCreateDTO dto) {

		Profissional profissional = new Profissional();
		profissional.setNome(dto.getNome());
		profissional.setEspecialidade(dto.getEspecialidade());
		profissional.setRegistroProfissional(dto.getRegistroProfissional());
		profissional.setEmail(dto.getEmail());
		profissional.setAtivo(true);

		if (dto.getTelefone() != null && !dto.getTelefone().isBlank()) {
			profissional.setTelefoneEnc(encryptionService.encryptMySQL(dto.getTelefone()));
		}

		profissionalRepository.save(profissional);
		return toProfissionalAdminDTO(profissional);
	}

	@Transactional
	public ProfissionalAdminDTO atualizarProfissionalAdminDTO(Long profissionalId, ProfissionalUpdateDTO dto) {

		Profissional profissional = profissionalRepository.findById(profissionalId)
				.orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado"));

		if (dto.getNome() != null)
			profissional.setNome(dto.getNome());
		if (dto.getEspecialidade() != null)
			profissional.setEspecialidade(dto.getEspecialidade());
		if (dto.getEmail() != null)
			profissional.setEmail(dto.getEmail());

		if (dto.getTelefone() != null && !dto.getTelefone().isBlank()) {
			profissional.setTelefoneEnc(encryptionService.encryptMySQL(dto.getTelefone()));
		}

		profissionalRepository.save(profissional);
		return toProfissionalAdminDTO(profissional);
	}

	@Transactional
	public void deletarProfissional(Long profissionalId) {

		Profissional profissional = profissionalRepository.findById(profissionalId)
				.orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado"));

		profissional.setAtivo(false);
		profissionalRepository.save(profissional);
	}

	private ProfissionalAdminDTO toProfissionalAdminDTO(Profissional profissional) {

		return new ProfissionalAdminDTO(profissional.getId(), profissional.getNome(), profissional.getEspecialidade(),
				profissional.getAtivo());
	}

//  INTERNAÇÕES

	@Transactional(readOnly = true)
	public List<InternacaoAdminDTO> listarInternacoes() {
		return internacaoRepository.findAll().stream().map(this::toInternacaoAdminDTO).toList();
	}

	@Transactional(readOnly = true)
	public InternacaoAdminDTO buscarInternacaoPorId(Long id) {
		Internacao internacao = internacaoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Internação não encontrada"));

		return toInternacaoAdminDTO(internacao);
	}

	@Transactional
	public InternacaoAdminDTO criarInternacao(InternacaoCreateDTO dto) {

		Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
				.orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

		Profissional profissional = profissionalRepository.findById(dto.getProfissionalId())
				.orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado"));

		Unidade unidade = unidadeRepository.findById(dto.getUnidadeId())
				.orElseThrow(() -> new ResourceNotFoundException("Unidade não encontrada"));

		Internacao internacao = new Internacao();
		internacao.setPaciente(paciente);
		internacao.setProfissionalResponsavel(profissional);
		internacao.setUnidade(unidade);
		internacao.setMotivo(dto.getMotivo());
		internacao.setDataEntrada(LocalDateTime.now());
		internacao.setStatus(StatusInternacao.ADMITIDA);

		internacaoRepository.save(internacao);
		return toInternacaoAdminDTO(internacao);
	}

	@Transactional
	public InternacaoAdminDTO atualizarStatusInternacao(Long internacaoId, InternacaoStatusUpdateDTO dto) {

		Internacao internacao = internacaoRepository.findById(internacaoId)
				.orElseThrow(() -> new ResourceNotFoundException("Internação não encontrada"));

		internacao.setStatus(StatusInternacao.valueOf(dto.getStatus()));
		internacaoRepository.save(internacao);

		return toInternacaoAdminDTO(internacao);
	}

	@Transactional
	public void encerrarInternacao(Long internacaoId) {
		Internacao internacao = internacaoRepository.findById(internacaoId)
				.orElseThrow(() -> new ResourceNotFoundException("Internação não encontrada"));

		internacao.encerrar();
		internacaoRepository.save(internacao);
	}

	@Transactional
	public void cancelarInternacao(Long internacaoId) {
		Internacao internacao = internacaoRepository.findById(internacaoId)
				.orElseThrow(() -> new ResourceNotFoundException("Internação não encontrada"));

		internacao.setStatus(StatusInternacao.CANCELADA);
		internacao.setDataAlta(LocalDateTime.now());
		internacaoRepository.save(internacao);
	}

	private InternacaoAdminDTO toInternacaoAdminDTO(Internacao i) {

		DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

		InternacaoAdminDTO dto = new InternacaoAdminDTO();
		dto.setId(i.getId());
		dto.setPacienteId(i.getPaciente().getId());
		dto.setPacienteNome(i.getPaciente().getNome());
		dto.setProfissionalId(i.getProfissionalResponsavel().getId());
		dto.setProfissionalNome(i.getProfissionalResponsavel().getNome());
		dto.setUnidadeId(i.getUnidade().getId());
		dto.setUnidadeNome(i.getUnidade().getNome());
		dto.setStatus(i.getStatus().name());
		dto.setMotivo(i.getMotivo());
		dto.setDataEntrada(i.getDataEntrada().format(fmt));
		dto.setDataAlta(i.getDataAlta() != null ? i.getDataAlta().format(fmt) : null);
		return dto;
	}

//  RELATÓRIOS

	@Transactional(readOnly = true)
	public long totalPacientes() {
		return pacienteRepository.count();
	}

	@Transactional(readOnly = true)
	public long totalProfissionais() {
		return profissionalRepository.count();
	}

	@Transactional(readOnly = true)
	public long internacoesAtivas() {
		return internacaoRepository.countByStatus(StatusInternacao.INTERNADA);
	}
}
