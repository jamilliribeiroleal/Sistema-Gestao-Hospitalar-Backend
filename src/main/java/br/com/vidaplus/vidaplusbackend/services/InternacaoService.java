package br.com.vidaplus.vidaplusbackend.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vidaplus.vidaplusbackend.dto.*;
import br.com.vidaplus.vidaplusbackend.entities.*;
import br.com.vidaplus.vidaplusbackend.enums.StatusInternacao;
import br.com.vidaplus.vidaplusbackend.exceptions.ResourceNotFoundException;
import br.com.vidaplus.vidaplusbackend.repositories.*;

@Service
public class InternacaoService {

	private final InternacaoRepository internacaoRepository;
	private final PacienteRepository pacienteRepository;
	private final ProfissionalRepository profissionalRepository;
	private final UnidadeRepository unidadeRepository;

	public InternacaoService(InternacaoRepository internacaoRepository, PacienteRepository pacienteRepository,
			ProfissionalRepository profissionalRepository, UnidadeRepository unidadeRepository) {

		this.internacaoRepository = internacaoRepository;
		this.pacienteRepository = pacienteRepository;
		this.profissionalRepository = profissionalRepository;
		this.unidadeRepository = unidadeRepository;
	}

	// CREATE
	@Transactional
	public InternacaoResponseDTO criar(InternacaoRequestDTO dto) {

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
		internacao.setDataEntrada(dto.getDataEntrada() != null ? dto.getDataEntrada() : LocalDateTime.now());
		internacao.setMotivo(dto.getMotivo());
		internacao.setStatus(dto.getStatus() != null ? StatusInternacao.valueOf(dto.getStatus().toUpperCase())
				: StatusInternacao.ADMITIDA);

		return toDTO(internacaoRepository.save(internacao));
	}

// READ
	@Transactional(readOnly = true)
	public List<InternacaoResponseDTO> listarTodas() {
		return internacaoRepository.findAll().stream().map(this::toDTO).toList();
	}

	@Transactional(readOnly = true)
	public InternacaoResponseDTO buscarPorId(Long id) {
		return toDTO(validar(id));
	}

	@Transactional(readOnly = true)
	public List<InternacaoResponseDTO> listarAtivas() {
		return internacaoRepository.findAll().stream().filter(Internacao::isAtiva).map(this::toDTO).toList();
	}

// UPDATE
	@Transactional
	public InternacaoResponseDTO atualizar(Long id, InternacaoRequestDTO dto) {

		Internacao internacao = validar(id);

		if (dto.getMotivo() != null)
			internacao.setMotivo(dto.getMotivo());

		if (dto.getStatus() != null) {
			internacao.setStatus(StatusInternacao.valueOf(dto.getStatus().toUpperCase()));
		}

		return toDTO(internacaoRepository.save(internacao));
	}

// ALTA
	@Transactional
	public void darAlta(Long id) {

		Internacao internacao = validar(id);

		internacao.setStatus(StatusInternacao.ALTA);
		internacao.setDataAlta(LocalDateTime.now());

		internacaoRepository.save(internacao);
	}

// AUX
	private Internacao validar(Long id) {
		return internacaoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Internação não encontrada"));
	}

	private InternacaoResponseDTO toDTO(Internacao i) {

		InternacaoResponseDTO dto = new InternacaoResponseDTO();
		dto.setId(i.getId());
		dto.setPacienteId(i.getPaciente().getId());
		dto.setPacienteNome(i.getPaciente().getNome());
		dto.setProfissionalId(i.getProfissionalResponsavel().getId());
		dto.setProfissionalNome(i.getProfissionalResponsavel().getNome());
		dto.setUnidadeId(i.getUnidade().getId());
		dto.setUnidadeNome(i.getUnidade().getNome());
		dto.setDataEntrada(i.getDataEntrada());
		dto.setDataAlta(i.getDataAlta());
		dto.setMotivo(i.getMotivo());
		dto.setStatus(i.getStatus().name());

		return dto;
	}
}
