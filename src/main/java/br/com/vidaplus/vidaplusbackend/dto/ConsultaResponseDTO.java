package br.com.vidaplus.vidaplusbackend.dto;

import br.com.vidaplus.vidaplusbackend.enums.StatusConsulta;

public class ConsultaResponseDTO {

    private Long id;

    private Long pacienteId;
    private String pacienteNome;

    private Long profissionalId;
    private String profissionalNome;

    private Long unidadeId;
    private String unidadeNome;

    private String dataHora; 

    private String tipo;
    private StatusConsulta status;
    private String motivo;

    private String linkTeleconsulta; 


    public ConsultaResponseDTO() {
    }

    public ConsultaResponseDTO(Long id,
                               Long pacienteId,
                               String pacienteNome,
                               Long profissionalId,
                               String profissionalNome,
                               Long unidadeId,
                               String unidadeNome,
                               String dataHora,
                               String tipo,
                               StatusConsulta status,
                               String motivo,
                               String linkTeleconsulta) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.pacienteNome = pacienteNome;
        this.profissionalId = profissionalId;
        this.profissionalNome = profissionalNome;
        this.unidadeId = unidadeId;
        this.unidadeNome = unidadeNome;
        this.dataHora = dataHora;
        this.tipo = tipo;
        this.status = status;
        this.motivo = motivo;
        this.linkTeleconsulta = linkTeleconsulta;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }

    public String getPacienteNome() { return pacienteNome; }
    public void setPacienteNome(String pacienteNome) { this.pacienteNome = pacienteNome; }

    public Long getProfissionalId() { return profissionalId; }
    public void setProfissionalId(Long profissionalId) { this.profissionalId = profissionalId; }

    public String getProfissionalNome() { return profissionalNome; }
    public void setProfissionalNome(String profissionalNome) { this.profissionalNome = profissionalNome; }

    public Long getUnidadeId() { return unidadeId; }
    public void setUnidadeId(Long unidadeId) { this.unidadeId = unidadeId; }

    public String getUnidadeNome() { return unidadeNome; }
    public void setUnidadeNome(String unidadeNome) { this.unidadeNome = unidadeNome; }

    public String getDataHora() { return dataHora; }
    public void setDataHora(String dataHora) { this.dataHora = dataHora; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public StatusConsulta getStatus() { return status; }
    public void setStatus(StatusConsulta status) { this.status = status; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getLinkTeleconsulta() { return linkTeleconsulta; }
    public void setLinkTeleconsulta(String linkTeleconsulta) { this.linkTeleconsulta = linkTeleconsulta; }
}
