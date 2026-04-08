package br.com.vidaplus.vidaplusbackend.dto;

import br.com.vidaplus.vidaplusbackend.entities.Teleconsulta;
import java.time.LocalDateTime;

public class TeleconsultaResponseDTO {

    private Long id;
    private PacienteResumoDTO paciente;
    private ProfissionalResumoDTO profissional;
    private Long consultaId;
    private String linkAcesso;
    private Teleconsulta.StatusChamada statusChamada;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    public TeleconsultaResponseDTO() {}

public static TeleconsultaResponseDTO fromEntity(Teleconsulta teleconsulta) {
        if (teleconsulta == null) return null;

        TeleconsultaResponseDTO dto = new TeleconsultaResponseDTO();
        dto.setId(teleconsulta.getId());


        dto.setPaciente(PacienteResumoDTO.fromEntity(teleconsulta.getPaciente()));
        dto.setProfissional(ProfissionalResumoDTO.fromEntity(teleconsulta.getProfissional()));

        if (teleconsulta.getConsulta() != null) {
            dto.setConsultaId(teleconsulta.getConsulta().getId());
        }

        dto.setLinkAcesso(teleconsulta.getLinkAcesso());
        dto.setStatusChamada(teleconsulta.getStatusChamada());
        dto.setDataInicio(teleconsulta.getDataInicio());
        dto.setDataFim(teleconsulta.getDataFim());

        return dto;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public PacienteResumoDTO getPaciente() { return paciente; }
    public void setPaciente(PacienteResumoDTO paciente) { this.paciente = paciente; }

    public ProfissionalResumoDTO getProfissional() { return profissional; }
    public void setProfissional(ProfissionalResumoDTO profissional) { this.profissional = profissional; }

    public Long getConsultaId() { return consultaId; }
    public void setConsultaId(Long consultaId) { this.consultaId = consultaId; }

    public String getLinkAcesso() { return linkAcesso; }
    public void setLinkAcesso(String linkAcesso) { this.linkAcesso = linkAcesso; }

    public Teleconsulta.StatusChamada getStatusChamada() { return statusChamada; }
    public void setStatusChamada(Teleconsulta.StatusChamada statusChamada) { this.statusChamada = statusChamada; }

    public LocalDateTime getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDateTime dataInicio) { this.dataInicio = dataInicio; }

    public LocalDateTime getDataFim() { return dataFim; }
    public void setDataFim(LocalDateTime dataFim) { this.dataFim = dataFim; }
}
