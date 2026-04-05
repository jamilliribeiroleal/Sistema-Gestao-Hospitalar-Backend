package br.com.vidaplus.vidaplusbackend.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "receitas",
       indexes = {
           @Index(columnList = "paciente_id"),
           @Index(columnList = "profissional_id"),
           @Index(columnList = "data_emissao")
       })
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;

    @Column(name = "data_emissao", nullable = false)
    private LocalDateTime dataEmissao;

    @Lob
    @Column(name = "conteudo_enc", nullable = false)
    private byte[] conteudoEnc;

    @Column(name = "receita_pdf_url", length = 1024)
    private String receitaPdfUrl;

    @Column(name = "assinatura_digital", length = 1024)
    private String assinaturaDigital;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Receita() {}

    public Receita(Long id, Consulta consulta, Paciente paciente, Profissional profissional,
                   LocalDateTime dataEmissao, byte[] conteudoEnc, String receitaPdfUrl,
                   String assinaturaDigital, LocalDateTime createdAt, LocalDateTime updatedAt,
                   LocalDateTime deletedAt) {
        this.id = id;
        this.consulta = consulta;
        this.paciente = paciente;
        this.profissional = profissional;
        this.dataEmissao = dataEmissao;
        this.conteudoEnc = conteudoEnc;
        this.receitaPdfUrl = receitaPdfUrl;
        this.assinaturaDigital = assinaturaDigital;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Consulta getConsulta() { return consulta; }
    public void setConsulta(Consulta consulta) { this.consulta = consulta; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Profissional getProfissional() { return profissional; }
    public void setProfissional(Profissional profissional) { this.profissional = profissional; }

    public LocalDateTime getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(LocalDateTime dataEmissao) { this.dataEmissao = dataEmissao; }

    public byte[] getConteudoEnc() { return conteudoEnc; }
    public void setConteudoEnc(byte[] conteudoEnc) { this.conteudoEnc = conteudoEnc; }

    public String getReceitaPdfUrl() { return receitaPdfUrl; }
    public void setReceitaPdfUrl(String receitaPdfUrl) { this.receitaPdfUrl = receitaPdfUrl; }

    public String getAssinaturaDigital() { return assinaturaDigital; }
    public void setAssinaturaDigital(String assinaturaDigital) { this.assinaturaDigital = assinaturaDigital; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public LocalDateTime getDeletedAt() { return deletedAt; }
    public void setDeletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; }

    @Override
    public String toString() {
        return "Receita{id=" + id + "}";
    }
}
