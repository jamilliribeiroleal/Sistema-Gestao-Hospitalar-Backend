package br.com.vidaplus.vidaplusbackend.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "teleconsultas")
public class Teleconsulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	private Paciente paciente;

	@ManyToOne(optional = false)
	private Profissional profissional;

	@OneToOne
	private Consulta consulta;

	private String linkAcesso;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusChamada statusChamada;

	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;

	public enum StatusChamada {
		AGENDADA, EM_ANDAMENTO, FINALIZADA, CANCELADA
	}

	public Teleconsulta() {
		this.statusChamada = StatusChamada.AGENDADA;
	}

	public Teleconsulta(Paciente paciente, Profissional profissional, Consulta consulta, String linkAcesso) {
		this.paciente = paciente;
		this.profissional = profissional;
		this.consulta = consulta;
		this.linkAcesso = linkAcesso;
		this.statusChamada = StatusChamada.AGENDADA;
	}

	public void iniciar() {
		this.statusChamada = StatusChamada.EM_ANDAMENTO;
		this.dataInicio = LocalDateTime.now();
	}

	public void cancelar() {
		this.statusChamada = StatusChamada.CANCELADA;
		this.dataFim = LocalDateTime.now();
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

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public String getLinkAcesso() {
		return linkAcesso;
	}

	public void setLinkAcesso(String linkAcesso) {
		this.linkAcesso = linkAcesso;
	}

	public StatusChamada getStatusChamada() {
		return statusChamada;
	}

	public void setStatusChamada(StatusChamada statusChamada) {
		this.statusChamada = statusChamada;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	@Override
	public String toString() {
		return "Teleconsulta{id=" + id + ", paciente=" + (paciente != null ? paciente.getId() : null)
				+ ", profissional=" + (profissional != null ? profissional.getId() : null) + ", statusChamada="
				+ statusChamada + "}";
	}
}
