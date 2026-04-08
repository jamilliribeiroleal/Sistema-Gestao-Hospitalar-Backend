package br.com.vidaplus.vidaplusbackend.dto;

public class ConsultaRequestDTO {

    private Long pacienteId;
    private Long profissionalId;
    private Long unidadeId;
  
    private String dataHora;

    private String tipo;

    private String motivo;

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }

    public Long getProfissionalId() { return profissionalId; }
    public void setProfissionalId(Long profissionalId) { this.profissionalId = profissionalId; }

    public Long getUnidadeId() { return unidadeId; }
    public void setUnidadeId(Long unidadeId) { this.unidadeId = unidadeId; }

    public String getDataHora() { return dataHora; }
    public void setDataHora(String dataHora) { this.dataHora = dataHora; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
}