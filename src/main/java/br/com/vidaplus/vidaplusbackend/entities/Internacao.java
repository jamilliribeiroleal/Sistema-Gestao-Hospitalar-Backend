package br.com.vidaplus.vidaplusbackend.entities;

import java.time.LocalDateTime;

import br.com.vidaplus.vidaplusbackend.enums.StatusInternacao;
import jakarta.persistence.*;

@Entity
@Table(name = "internacoes")
public class Internacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	@ManyToOne(optional = false)
	@JoinColumn(name = "profissional_responsavel_id")
	private Profissional profissionalResponsavel;

	@ManyToOne(optional = false)
	@JoinColumn(name = "unidade_id")
	private Unidade unidade;

	@Column(nullable = false)
	private LocalDateTime dataEntrada;

	private LocalDateTime dataAlta;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusInternacao status;

	@Column(length = 500)
	private String motivo;

	public void encerrar() {
		if (!isAtiva()) {
			throw new IllegalStateException("Não é possível dar alta. A internação não está ativa.");
		}

		this.status = StatusInternacao.ALTA;
		this.dataAlta = LocalDateTime.now();
	}

	public boolean isAtiva() {
		return this.status == StatusInternacao.ADMITIDA || this.status == StatusInternacao.EM_OBSERVACAO
				|| this.status == StatusInternacao.INTERNADA;
	}

	public Long getId() {
		return id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Profissional getProfissionalResponsavel() {
		return profissionalResponsavel;
	}

	public void setProfissionalResponsavel(Profissional profissionalResponsavel) {
		this.profissionalResponsavel = profissionalResponsavel;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDateTime getDataAlta() {
		return dataAlta;
	}

	public StatusInternacao getStatus() {
		return status;
	}

	public void setStatus(StatusInternacao status) {
		this.status = status;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDataAlta(LocalDateTime dataAlta) {
		this.dataAlta = dataAlta;
	}
}
