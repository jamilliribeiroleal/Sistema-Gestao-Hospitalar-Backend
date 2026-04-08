package br.com.vidaplus.vidaplusbackend.dto;

import java.time.LocalDateTime;
import br.com.vidaplus.vidaplusbackend.enums.StatusConsulta;

public class AgendaProfissionalDTO {

    private LocalDateTime dataHora;
    private StatusConsulta status;

    public AgendaProfissionalDTO(LocalDateTime dataHora,
                                  StatusConsulta status) {
        this.dataHora = dataHora;
        this.status = status;
    }

    public LocalDateTime getDataHora() { return dataHora; }
    public StatusConsulta getStatus() { return status; }
}
