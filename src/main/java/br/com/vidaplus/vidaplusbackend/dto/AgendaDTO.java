package br.com.vidaplus.vidaplusbackend.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AgendaDTO {

    private Long consultaId;
    private Long pacienteId;
    private String pacienteNome;
    private LocalDateTime dataHora;
    private String status;

    @JsonProperty("teleconsulta")
    private boolean teleconsulta;

    public AgendaDTO() {}

    public AgendaDTO(
            Long consultaId,
            Long pacienteId,
            String pacienteNome,
            LocalDateTime dataHora,
            String status,
            boolean teleconsulta) {

        this.consultaId = consultaId;
        this.pacienteId = pacienteId;
        this.pacienteNome = pacienteNome;
        this.dataHora = dataHora;
        this.status = status;
        this.teleconsulta = teleconsulta;
    }

    public Long getConsultaId() {
        return consultaId;
    }

    public void setConsultaId(Long consultaId) {
        this.consultaId = consultaId;
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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isTeleconsulta() {
        return teleconsulta;
    }

    public void setTeleconsulta(boolean teleconsulta) {
        this.teleconsulta = teleconsulta;
    }
}
