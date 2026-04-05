package br.com.vidaplus.vidaplusbackend.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "unidades")
public class Unidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UnidadeTipo tipo;

	@Column(name = "endereco_enc")
	private byte[] enderecoEnc;

	@Column(name = "telefone_enc")
	private byte[] telefoneEnc;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	@OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Leito> leitos = new ArrayList<>();

	@OneToMany(mappedBy = "unidade", fetch = FetchType.LAZY)
	private List<Consulta> consultas = new ArrayList<>();

	public Unidade() {
	}

	public Unidade(Long id, String nome, UnidadeTipo tipo) {
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UnidadeTipo getTipo() {
		return tipo;
	}

	public void setTipo(UnidadeTipo tipo) {
		this.tipo = tipo;
	}

	public byte[] getEnderecoEnc() {
		return enderecoEnc;
	}

	public void setEnderecoEnc(byte[] enderecoEnc) {
		this.enderecoEnc = enderecoEnc;
	}

	public byte[] getTelefoneEnc() {
		return telefoneEnc;
	}

	public void setTelefoneEnc(byte[] telefoneEnc) {
		this.telefoneEnc = telefoneEnc;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	public List<Leito> getLeitos() {
		return leitos;
	}

	public void setLeitos(List<Leito> leitos) {
		this.leitos = leitos;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	@Override
	public String toString() {
		return "Unidade{id=" + id + ", nome='" + nome + "', tipo=" + tipo + "}";
	}

	public enum UnidadeTipo {
		HOSPITAL, CLINICA, LABORATORIO, HOMECARE
	}
}
