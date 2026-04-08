package br.com.vidaplus.vidaplusbackend.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProntuarioResponseDTO {

	private Long id;
	private Long pacienteId;
	private Long profissionalId;
	private Long consultaId;

	private String profissionalNome;

	private String descricao;
	private String diagnostico;
	private String observacoes;

	@JsonProperty("dataRegistro")
	private LocalDateTime createdAt;

	public ProntuarioResponseDTO(Long id, Long pacienteId, Long profissionalId, Long consultaId,
			String profissionalNome, String descricao, String diagnostico, String observacoes,
			LocalDateTime createdAt) {
		this.id = id;
		this.pacienteId = pacienteId;
		this.profissionalId = profissionalId;
		this.consultaId = consultaId;
		this.profissionalNome = profissionalNome;
		this.descricao = descricao;
		this.diagnostico = diagnostico;
		this.observacoes = observacoes;
		this.createdAt = createdAt;
	}

	public ProntuarioResponseDTO() {
	}

	public Long getId() {
		return id;
	}

	public Long getPacienteId() {
		return pacienteId;
	}

	public Long getProfissionalId() {
		return profissionalId;
	}

	public Long getConsultaId() {
		return consultaId;
	}

	public String getProfissionalNome() {
		return profissionalNome;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}

	public void setProfissionalId(Long profissionalId) {
		this.profissionalId = profissionalId;
	}

	public void setConsultaId(Long consultaId) {
		this.consultaId = consultaId;
	}

	public void setProfissionalNome(String profissionalNome) {
		this.profissionalNome = profissionalNome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
