package br.com.vidaplus.vidaplusbackend.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "receitas_digitais")
public class ReceitaDigital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "paciente_id", nullable = false)
	private Paciente paciente;

	@ManyToOne(optional = false)
	@JoinColumn(name = "profissional_id", nullable = false)
	private Profissional profissional;

	@ManyToOne
	@JoinColumn(name = "consulta_id")
	private Consulta consulta;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String medicamentos;

	@Column(columnDefinition = "TEXT")
	private String orientacoes;

	@Column(nullable = false)
	private LocalDateTime dataEmissao;

	@Column(nullable = false)
	private boolean ativa;

	public ReceitaDigital() {
		this.dataEmissao = LocalDateTime.now();
		this.ativa = true;
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

	public String getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(String medicamentos) {
		this.medicamentos = medicamentos;
	}

	public String getOrientacoes() {
		return orientacoes;
	}

	public void setOrientacoes(String orientacoes) {
		this.orientacoes = orientacoes;
	}

	public LocalDateTime getDataEmissao() {
		return dataEmissao;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void desativar() {
		this.ativa = false;
	}
}
