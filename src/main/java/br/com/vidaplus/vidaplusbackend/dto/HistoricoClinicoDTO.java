package br.com.vidaplus.vidaplusbackend.dto;

import java.time.LocalDateTime;

public class HistoricoClinicoDTO {

    private Long id;
    private LocalDateTime dataRegistro;

    private String descricao;
    private String diagnostico;
    private String observacoes;

    private Long consultaId;
    private String profissionalNome;

    public HistoricoClinicoDTO() {}

    public HistoricoClinicoDTO(
            Long id,
            LocalDateTime dataRegistro,
            String descricao,
            String diagnostico,
            String observacoes,
            Long consultaId,
            String profissionalNome) {

        this.id = id;
        this.dataRegistro = dataRegistro;
        this.descricao = descricao;
        this.diagnostico = diagnostico;
        this.observacoes = observacoes;
        this.consultaId = consultaId;
        this.profissionalNome = profissionalNome;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(LocalDateTime dataRegistro) { this.dataRegistro = dataRegistro; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public Long getConsultaId() { return consultaId; }
    public void setConsultaId(Long consultaId) { this.consultaId = consultaId; }

    public String getProfissionalNome() { return profissionalNome; }
    public void setProfissionalNome(String profissionalNome) {
        this.profissionalNome = profissionalNome;
    }
}
