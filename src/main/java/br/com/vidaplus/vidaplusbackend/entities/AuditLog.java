package br.com.vidaplus.vidaplusbackend.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log",
       indexes = {
           @Index(columnList = "entidade, entidade_id"),
           @Index(columnList = "user_id")
       })
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String entidade;

    @Column(name = "entidade_id")
    private Long entidadeId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuditAcao acao;

    @Column(columnDefinition = "JSON")
    private String detalhes;

    @Column(length = 45)
    private String ip;

    @Column(name = "correlation_id", length = 100)
    private String correlationId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public AuditLog() {}

    public AuditLog(Long id, User user, String entidade, Long entidadeId, AuditAcao acao, String detalhes,
                    String ip, String correlationId, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.entidade = entidade;
        this.entidadeId = entidadeId;
        this.acao = acao;
        this.detalhes = detalhes;
        this.ip = ip;
        this.correlationId = correlationId;
        this.createdAt = createdAt;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getEntidade() { return entidade; }
    public void setEntidade(String entidade) { this.entidade = entidade; }

    public Long getEntidadeId() { return entidadeId; }
    public void setEntidadeId(Long entidadeId) { this.entidadeId = entidadeId; }

    public AuditAcao getAcao() { return acao; }
    public void setAcao(AuditAcao acao) { this.acao = acao; }

    public String getDetalhes() { return detalhes; }
    public void setDetalhes(String detalhes) { this.detalhes = detalhes; }

    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }

    public String getCorrelationId() { return correlationId; }
    public void setCorrelationId(String correlationId) { this.correlationId = correlationId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "AuditLog{id=" + id + ", entidade='" + entidade + "'}";
    }

    public enum AuditAcao { CREATE, READ, UPDATE, DELETE }
}
