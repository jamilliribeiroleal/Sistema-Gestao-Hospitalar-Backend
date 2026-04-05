package br.com.vidaplus.vidaplusbackend.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.vidaplus.vidaplusbackend.enums.StatusConsulta;
import br.com.vidaplus.vidaplusbackend.enums.TipoConsulta;

@Entity
@Table(name = "consultas", indexes = { @Index(columnList = "paciente_id"), @Index(columnList = "profissional_id"),
		@Index(columnList = "data_hora") })
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "paciente_id", nullable = false)
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "profissional_id")
	private Profissional profissional;

	@ManyToOne
	@JoinColumn(name = "unidade_id")
	private Unidade unidade;

// DADOS DA CONSULTA
	@Column(name = "data_hora", nullable = false)
	private LocalDateTime dataHora;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false)
	private TipoConsulta tipo = TipoConsulta.PRESENCIAL;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private StatusConsulta status = StatusConsulta.AGENDADA;

	@Column(length = 512)
	private String motivo;

	@Column(name = "link_teleconsulta")
	private String linkTeleconsulta;

	// AUDITORIA
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	// RELAÇÕES CLÍNICAS
	@OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Prontuario> prontuarios = new ArrayList<>();

	@OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Receita> receitas = new ArrayList<>();

	public Consulta() {
	}

	public boolean isTeleconsulta() {
		return TipoConsulta.TELECONSULTA.equals(this.tipo);
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public TipoConsulta getTipo() {
		return tipo;
	}

	public void setTipo(TipoConsulta tipo) {
		this.tipo = tipo;
	}

	public StatusConsulta getStatus() {
		return status;
	}

	public void setStatus(StatusConsulta status) {
		this.status = status;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getLinkTeleconsulta() {
		return linkTeleconsulta;
	}

	public void setLinkTeleconsulta(String linkTeleconsulta) {
		this.linkTeleconsulta = linkTeleconsulta;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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

	public List<Prontuario> getProntuarios() {
		return prontuarios;
	}

	public void setProntuarios(List<Prontuario> prontuarios) {
		this.prontuarios = prontuarios;
	}

	public List<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	@Override
	public String toString() {
		return "Consulta{id=" + id + ", dataHora=" + dataHora + ", tipo=" + tipo + "}";
	}
}
