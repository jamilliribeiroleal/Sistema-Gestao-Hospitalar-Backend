package br.com.vidaplus.vidaplusbackend.dto;

import java.time.LocalDateTime;

public class InternacaoRequestDTO {

    private Long pacienteId;
    private Long profissionalId;
    private Long unidadeId;
    private LocalDateTime dataEntrada;
    private String motivo;
    private String status;
    
	public Long getPacienteId() {
		return pacienteId;
	}
	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}
	public Long getProfissionalId() {
		return profissionalId;
	}
	public void setProfissionalId(Long profissionalId) {
		this.profissionalId = profissionalId;
	}
	public Long getUnidadeId() {
		return unidadeId;
	}
	public void setUnidadeId(Long unidadeId) {
		this.unidadeId = unidadeId;
	}
	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
