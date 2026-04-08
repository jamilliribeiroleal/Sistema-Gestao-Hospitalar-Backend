package br.com.vidaplus.vidaplusbackend.dto;
public class InternacaoCreateDTO {

    private Long pacienteId;
    private Long profissionalId;
    private Long unidadeId;
    private String motivo;
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
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
}
