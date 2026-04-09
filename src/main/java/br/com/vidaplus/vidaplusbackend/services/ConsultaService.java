package br.com.vidaplus.vidaplusbackend.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vidaplus.vidaplusbackend.dto.ConsultaRequestDTO;
import br.com.vidaplus.vidaplusbackend.dto.ConsultaResponseDTO;
import br.com.vidaplus.vidaplusbackend.entities.Consulta;
import br.com.vidaplus.vidaplusbackend.entities.Paciente;
import br.com.vidaplus.vidaplusbackend.entities.Profissional;
import br.com.vidaplus.vidaplusbackend.entities.Unidade;
import br.com.vidaplus.vidaplusbackend.enums.StatusConsulta;
import br.com.vidaplus.vidaplusbackend.exceptions.ResourceNotFoundException;
import br.com.vidaplus.vidaplusbackend.mappers.ConsultaMapper;
import br.com.vidaplus.vidaplusbackend.repositories.ConsultaRepository;
import br.com.vidaplus.vidaplusbackend.repositories.PacienteRepository;
import br.com.vidaplus.vidaplusbackend.repositories.ProfissionalRepository;
import br.com.vidaplus.vidaplusbackend.repositories.UnidadeRepository;

@Service
public class ConsultaService {

	private final ConsultaRepository consultaRepository;
	private final PacienteRepository pacienteRepository;
	private final ProfissionalRepository profissionalRepository;
	private final UnidadeRepository unidadeRepository;
	private final NotificacaoService notificacaoService;

	public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository,
			ProfissionalRepository profissionalRepository, UnidadeRepository unidadeRepository,
			NotificacaoService notificacaoService) {

		this.consultaRepository = consultaRepository;
		this.pacienteRepository = pacienteRepository;
		this.profissionalRepository = profissionalRepository;
		this.unidadeRepository = unidadeRepository;
		this.notificacaoService = notificacaoService;
	}

// LISTAR TODAS
	@Transactional(readOnly = true)
	public List<ConsultaResponseDTO> findAll() {
		return consultaRepository.findAll().stream().map(ConsultaMapper::toDTO).toList();
	}

// BUSCAR POR ID
	@Transactional(readOnly = true)
	public ConsultaResponseDTO findById(Long id) {
		return ConsultaMapper.toDTO(getEntityById(id));
	}

	@Transactional(readOnly = true)
	public Consulta getEntityById(Long id) {
		return consultaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com id: " + id));
	}

// AGENDAR CONSULTA
	@Transactional
	public ConsultaResponseDTO agendarConsulta(ConsultaRequestDTO dto) {

		Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
				.orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

		Profissional profissional = dto.getProfissionalId() != null
				? profissionalRepository.findById(dto.getProfissionalId()).orElseThrow(
						() -> new ResourceNotFoundException("Profissional não encontrado"))
				: null;

		Unidade unidade = dto.getUnidadeId() != null ? unidadeRepository.findById(dto.getUnidadeId())
				.orElseThrow(() -> new ResourceNotFoundException("Unidade não encontrada")) : null;

		// Cria entidade a partir do DTO
		Consulta consulta = ConsultaMapper.toEntity(dto, paciente, profissional, unidade);

		consulta = consultaRepository.save(consulta);

		notificacaoService.criarNotificacao(paciente, "Consulta agendada",
				"Sua consulta foi agendada para " + consulta.getDataHora());

		return ConsultaMapper.toDTO(consulta);
	}

// CANCELAR CONSULTA
	@Transactional
	public void cancelarConsulta(Long consultaId) {

		Consulta consulta = getEntityById(consultaId);

		consulta.setStatus(StatusConsulta.CANCELADA);

		// Salva a alteração no banco
		consultaRepository.save(consulta);

		notificacaoService.criarNotificacao(consulta.getPaciente(), "Consulta cancelada",
				"Sua consulta marcada para " + consulta.getDataHora() + " foi cancelada.");
	}

// LISTAR CONSULTAS POR PACIENTE
	@Transactional(readOnly = true)
	public List<ConsultaResponseDTO> findByPaciente(Long pacienteId) {

		pacienteRepository.findById(pacienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

		return consultaRepository.findByPacienteId(pacienteId).stream().map(ConsultaMapper::toDTO).toList();
	}

	// VERIFICAR TELECONSULTA
	@Transactional(readOnly = true)
	public boolean isTeleconsulta(Long consultaId) {
		return getEntityById(consultaId).isTeleconsulta();
	}
}
