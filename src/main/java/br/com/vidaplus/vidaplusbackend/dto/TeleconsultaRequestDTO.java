package br.com.vidaplus.vidaplusbackend.dto;

import br.com.vidaplus.vidaplusbackend.entities.Teleconsulta;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TeleconsultaRequestDTO {

    @NotNull
    private Long pacienteId;

    @NotNull
    private Long profissionalId;

    private Long consultaId;

    private String linkAcesso;

    private Teleconsulta.StatusChamada statusChamada;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

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

    public Long getConsultaId() {
        return consultaId;
    }

    public void setConsultaId(Long consultaId) {
        this.consultaId = consultaId;
    }

    public String getLinkAcesso() {
        return linkAcesso;
    }

    public void setLinkAcesso(String linkAcesso) {
        this.linkAcesso = linkAcesso;
    }

    public Teleconsulta.StatusChamada getStatusChamada() {
        return statusChamada;
    }

    public void setStatusChamada(Teleconsulta.StatusChamada statusChamada) {
        this.statusChamada = statusChamada;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }
}
