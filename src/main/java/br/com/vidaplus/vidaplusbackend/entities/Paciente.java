package br.com.vidaplus.vidaplusbackend.entities;

import br.com.vidaplus.vidaplusbackend.enums.PacienteSexo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pacientes")
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(name = "cpf_hash", length = 64, unique = true)
	private String cpfHash;

	@Column(name = "cpf_enc")
	private byte[] cpfEnc;

	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	@Enumerated(EnumType.STRING)
	@Column(name = "sexo", length = 1, nullable = false)
	private PacienteSexo sexo;

	@Column
	private String email;

	@Column(name = "telefone_enc")
	private byte[] telefoneEnc;

	@Column(name = "endereco_enc")
	private byte[] enderecoEnc;

	@Column(nullable = false)
	private Boolean ativo = true;

	// AUDITORIA
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	// RELACIONAMENTOS

	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Consulta> consultas = new ArrayList<>();

	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Prontuario> prontuarios = new ArrayList<>();

	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Receita> receitas = new ArrayList<>();

	@OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
	private User user;

	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
		this.ativo = true;
	}

	@PreUpdate
	public void preUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpfHash() {
		return cpfHash;
	}

	public byte[] getCpfEnc() {
		return cpfEnc;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public PacienteSexo getSexo() {
		return sexo;
	}

	public String getEmail() {
		return email;
	}

	public byte[] getTelefoneEnc() {
		return telefoneEnc;
	}

	public byte[] getEnderecoEnc() {
		return enderecoEnc;
	}

	public Boolean getAtivo() {
		return ativo;
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

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public List<Prontuario> getProntuarios() {
		return prontuarios;
	}

	public List<Receita> getReceitas() {
		return receitas;
	}

	public User getUser() {
		return user;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpfHash(String cpfHash) {
		this.cpfHash = cpfHash;
	}

	public void setCpfEnc(byte[] cpfEnc) {
		this.cpfEnc = cpfEnc;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setSexo(PacienteSexo sexo) {
		this.sexo = sexo;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefoneEnc(byte[] telefoneEnc) {
		this.telefoneEnc = telefoneEnc;
	}

	public void setEnderecoEnc(byte[] enderecoEnc) {
		this.enderecoEnc = enderecoEnc;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Paciente{id=" + id + ", nome='" + nome + "'}";
	}
}
