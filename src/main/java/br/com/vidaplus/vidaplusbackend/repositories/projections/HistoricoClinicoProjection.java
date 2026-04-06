package br.com.vidaplus.vidaplusbackend.repositories.projections;

import java.time.LocalDateTime;

public interface HistoricoClinicoProjection {

    Long getId();

    Long getPacienteId();

    Long getProfissionalId();

    Long getConsultaId();

    String getProfissionalNome(); 

    String getDescricao();

    String getDiagnostico();

    String getObservacoes();

    LocalDateTime getDataRegistro(); 
}
