package br.com.vidaplus.vidaplusbackend.dto;
public class InternacaoAdminDTO {

    private Long id;
    private Long pacienteId;
    private String pacienteNome;
    private Long profissionalId;
    private String profissionalNome;
    private Long unidadeId;
    private String unidadeNome;
    private String status;
    private String motivo;
    private String dataEntrada;
    private String dataAlta;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPacienteId() {
		return pacienteId;
	}
	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}
	public String getPacienteNome() {
		return pacienteNome;
	}
	public void setPacienteNome(String pacienteNome) {
		this.pacienteNome = pacienteNome;
	}
	public Long getProfissionalId() {
		return profissionalId;
	}
	public void setProfissionalId(Long profissionalId) {
		this.profissionalId = profissionalId;
	}
	public String getProfissionalNome() {
		return profissionalNome;
	}
	public void setProfissionalNome(String profissionalNome) {
		this.profissionalNome = profissionalNome;
	}
	public Long getUnidadeId() {
		return unidadeId;
	}
	public void setUnidadeId(Long unidadeId) {
		this.unidadeId = unidadeId;
	}
	public String getUnidadeNome() {
		return unidadeNome;
	}
	public void setUnidadeNome(String unidadeNome) {
		this.unidadeNome = unidadeNome;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public String getDataAlta() {
		return dataAlta;
	}
	public void setDataAlta(String dataAlta) {
		this.dataAlta = dataAlta;
	}
}
