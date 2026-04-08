package br.com.vidaplus.vidaplusbackend.dto;

import java.time.LocalDateTime;

import br.com.vidaplus.vidaplusbackend.enums.StatusTeleconsulta;

public class TeleconsultaDTO {

    private Long consultaId;
    private LocalDateTime dataHora;
    private StatusTeleconsulta status;
    private String linkAcesso;

    public TeleconsultaDTO() {}

    public TeleconsultaDTO(
            Long consultaId,
            LocalDateTime dataHora,
            StatusTeleconsulta status,
            String linkAcesso) {

        this.consultaId = consultaId;
        this.dataHora = dataHora;
        this.status = status;
        this.linkAcesso = linkAcesso;
    }

    public Long getConsultaId() { return consultaId; }
    public void setConsultaId(Long consultaId) { this.consultaId = consultaId; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public StatusTeleconsulta getStatus() { return status; }
    public void setStatus(StatusTeleconsulta status) { this.status = status; }

    public String getLinkAcesso() { return linkAcesso; }
    public void setLinkAcesso(String linkAcesso) { this.linkAcesso = linkAcesso; }
}
