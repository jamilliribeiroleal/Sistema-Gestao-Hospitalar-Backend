package br.com.vidaplus.vidaplusbackend.dto;

public class RelatorioResumoDTO {

	private long totalPacientes;
	private long totalProfissionais;
	private long internacoesAtivas;

	public RelatorioResumoDTO() {
	}

	public RelatorioResumoDTO(long totalPacientes, long totalProfissionais, long internacoesAtivas) {

		this.totalPacientes = totalPacientes;
		this.totalProfissionais = totalProfissionais;
		this.internacoesAtivas = internacoesAtivas;
	}

	public long getTotalPacientes() {
		return totalPacientes;
	}

	public void setTotalPacientes(long totalPacientes) {
		this.totalPacientes = totalPacientes;
	}

	public long getTotalProfissionais() {
		return totalProfissionais;
	}

	public void setTotalProfissionais(long totalProfissionais) {
		this.totalProfissionais = totalProfissionais;
	}

	public long getInternacoesAtivas() {
		return internacoesAtivas;
	}

	public void setInternacoesAtivas(long internacoesAtivas) {
		this.internacoesAtivas = internacoesAtivas;
	}
}
