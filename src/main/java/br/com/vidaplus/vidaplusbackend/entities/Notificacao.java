package br.com.vidaplus.vidaplusbackend.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificacoes", indexes = { @Index(columnList = "paciente_id"), @Index(columnList = "lida") })
public class Notificacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(optional = false)
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "consulta_id")
	private Consulta consulta;

	@Column(nullable = false, length = 255)
	private String titulo;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String mensagem;

	@Column(nullable = false)
	private boolean lida = false;

	@Column(nullable = false)
	private LocalDateTime dataCriacao;

	public Notificacao() {
		this.dataCriacao = LocalDateTime.now();
	}

	public Notificacao(Paciente paciente, String titulo, String mensagem) {
		this();
		this.paciente = paciente;
		this.titulo = titulo;
		this.mensagem = mensagem;
	}

	public Notificacao(Paciente paciente, Consulta consulta, String titulo, String mensagem) {
		this(paciente, titulo, mensagem);
		this.consulta = consulta;
	}

	public void marcarComoLida() {
		this.lida = true;
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

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public boolean isLida() {
		return lida;
	}

	public void setLida(boolean lida) {
		this.lida = lida;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
}
