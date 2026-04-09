package br.com.vidaplus.vidaplusbackend.services;

import br.com.vidaplus.vidaplusbackend.dto.TeleconsultaRequestDTO;
import br.com.vidaplus.vidaplusbackend.entities.*;
import br.com.vidaplus.vidaplusbackend.exceptions.ResourceNotFoundException;
import br.com.vidaplus.vidaplusbackend.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeleconsultaService {

	private final TeleconsultaRepository teleconsultaRepository;
	private final ProntuarioRepository prontuarioRepository;
	private final ReceitaDigitalRepository receitaDigitalRepository;
	private final PacienteRepository pacienteRepository;
	private final ProfissionalRepository profissionalRepository;
	private final ConsultaRepository consultaRepository;

	public TeleconsultaService(TeleconsultaRepository teleconsultaRepository, ProntuarioRepository prontuarioRepository,
			ReceitaDigitalRepository receitaDigitalRepository, PacienteRepository pacienteRepository,
			ProfissionalRepository profissionalRepository, ConsultaRepository consultaRepository) {

		this.teleconsultaRepository = teleconsultaRepository;
		this.prontuarioRepository = prontuarioRepository;
		this.receitaDigitalRepository = receitaDigitalRepository;
		this.pacienteRepository = pacienteRepository;
		this.profissionalRepository = profissionalRepository;
		this.consultaRepository = consultaRepository;
	}

	// VIDEOCHAMADAS

	@Transactional(readOnly = true)
	public List<Teleconsulta> findAll() {
		return teleconsultaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Teleconsulta findById(Long id) {
		return teleconsultaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Teleconsulta não encontrada com id: " + id));
	}

	@Transactional
	public Teleconsulta criarTeleconsulta(TeleconsultaRequestDTO dto) {

		// Buscar paciente
		Paciente paciente = pacienteRepository.findById(dto.getPacienteId()).orElseThrow(
				() -> new ResourceNotFoundException("Paciente não encontrado com id: " + dto.getPacienteId()));

		// Buscar profissional
		Profissional profissional = profissionalRepository.findById(dto.getProfissionalId()).orElseThrow(
				() -> new ResourceNotFoundException("Profissional não encontrado com id: " + dto.getProfissionalId()));

		// Buscar consulta, se fornecida
		Consulta consulta = null;
		if (dto.getConsultaId() != null) {
			consulta = consultaRepository.findById(dto.getConsultaId()).orElseThrow(
					() -> new ResourceNotFoundException("Consulta não encontrada com id: " + dto.getConsultaId()));
		}

		// Criar teleconsulta
		Teleconsulta teleconsulta = new Teleconsulta();
		teleconsulta.setPaciente(paciente);
		teleconsulta.setProfissional(profissional);
		teleconsulta.setConsulta(consulta);
		teleconsulta.setLinkAcesso(dto.getLinkAcesso());
		teleconsulta.setStatusChamada(
				dto.getStatusChamada() != null ? dto.getStatusChamada() : Teleconsulta.StatusChamada.AGENDADA);
		teleconsulta.setDataInicio(dto.getDataInicio());
		teleconsulta.setDataFim(dto.getDataFim());

		return teleconsultaRepository.save(teleconsulta);
	}

	@Transactional
	public Teleconsulta atualizarStatus(Long id, Teleconsulta.StatusChamada status) {
		Teleconsulta teleconsulta = findById(id);
		teleconsulta.setStatusChamada(status);
		return teleconsultaRepository.save(teleconsulta);
	}

//  REGISTRAR PRONTUÁRIO ONLINE

	@Transactional
	public Prontuario registrarProntuario(Long teleconsultaId, Prontuario prontuario) {
		Teleconsulta teleconsulta = findById(teleconsultaId);
		prontuario.setConsulta(teleconsulta.getConsulta());
		prontuario.setPaciente(teleconsulta.getPaciente());
		prontuario.setProfissional(teleconsulta.getProfissional());
		return prontuarioRepository.save(prontuario);
	}

//  EMITIR RECEITA DIGITAL ONLINE

	@Transactional
	public ReceitaDigital emitirReceita(Long teleconsultaId, ReceitaDigital receita) {
		Teleconsulta teleconsulta = findById(teleconsultaId);
		receita.setPaciente(teleconsulta.getPaciente());
		receita.setProfissional(teleconsulta.getProfissional());
		receita.setConsulta(teleconsulta.getConsulta());
		return receitaDigitalRepository.save(receita);
	}

	// CANCELAR TELECONSULTA

	@Transactional
	public void cancelar(Long id) {
		Teleconsulta teleconsulta = findById(id);
		teleconsulta.cancelar();
		teleconsultaRepository.save(teleconsulta);
	}
}
