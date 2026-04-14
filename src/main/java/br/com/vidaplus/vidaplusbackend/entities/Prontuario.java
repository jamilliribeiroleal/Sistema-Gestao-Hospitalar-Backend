package br.com.vidaplus.vidaplusbackend.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "prontuarios",
    indexes = {
        @Index(columnList = "paciente_id"),
        @Index(columnList = "consulta_id"),
        @Index(columnList = "profissional_id")
    }
)
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    // DADOS CLÍNICOS (CRIPTOGRAFADOS)
    @Lob
    @Column(
        name = "descricao_enc",
        nullable = false,
        columnDefinition = "BLOB"
    )
    private byte[] descricaoEnc;

    @Lob
    @Column(
        name = "diagnostico_enc",
        columnDefinition = "BLOB"
    )
    private byte[] diagnosticoEnc;

    @Lob
    @Column(
        name = "observacoes_enc",
        columnDefinition = "BLOB"
    )
    private byte[] observacoesEnc;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;


    public Prontuario() {}

    // CALLBACKS JPA

    @PrePersist
    protected void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public byte[] getDescricaoEnc() {
        return descricaoEnc;
    }

    public byte[] getDiagnosticoEnc() {
        return diagnosticoEnc;
    }

    public byte[] getObservacoesEnc() {
        return observacoesEnc;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public void setDescricaoEnc(byte[] descricaoEnc) {
        this.descricaoEnc = descricaoEnc;
    }

    public void setDiagnosticoEnc(byte[] diagnosticoEnc) {
        this.diagnosticoEnc = diagnosticoEnc;
    }

    public void setObservacoesEnc(byte[] observacoesEnc) {
        this.observacoesEnc = observacoesEnc;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
