package br.com.vidaplus.vidaplusbackend.entities;

import br.com.vidaplus.vidaplusbackend.enums.LeitoStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "leitos", uniqueConstraints = @UniqueConstraint(columnNames = { "unidade_id", "numero" }))
public class Leito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "unidade_id", nullable = false)
	private Unidade unidade;

	@Column(nullable = false)
	private String numero;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private LeitoStatus status = LeitoStatus.LIVRE;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	public Leito() {
	}

	public Leito(Long id, Unidade unidade, String numero, LeitoStatus status) {
		this.id = id;
		this.unidade = unidade;
		this.numero = numero;
		this.status = status;
	}

	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public LeitoStatus getStatus() {
		return status;
	}

	public void setStatus(LeitoStatus status) {
		this.status = status;
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

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	@Override
	public String toString() {
		return "Leito{id=" + id + ", numero='" + numero + "', status=" + status + "}";
	}
}
