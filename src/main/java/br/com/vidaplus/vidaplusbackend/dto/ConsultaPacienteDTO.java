package br.com.vidaplus.vidaplusbackend.dto;

import java.time.LocalDateTime;
import br.com.vidaplus.vidaplusbackend.enums.StatusConsulta;

public class ConsultaPacienteDTO {

    private Long id;
    private LocalDateTime dataHora;
    private StatusConsulta status;

    public ConsultaPacienteDTO(Long id,
                               LocalDateTime dataHora,
                               StatusConsulta status) {
        this.id = id;
        this.dataHora = dataHora;
        this.status = status;
    }

    public Long getId() { return id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public StatusConsulta getStatus() { return status; }
}
