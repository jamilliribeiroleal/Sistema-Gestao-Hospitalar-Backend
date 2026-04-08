package br.com.vidaplus.vidaplusbackend.dto;

public class AdminResumoDTO {

    private Long totalPacientes;
    private Long totalProfissionais;
    private Long internacoesAtivas;

    public AdminResumoDTO(Long totalPacientes,
                           Long totalProfissionais,
                           Long internacoesAtivas) {
        this.totalPacientes = totalPacientes;
        this.totalProfissionais = totalProfissionais;
        this.internacoesAtivas = internacoesAtivas;
    }

    public Long getTotalPacientes() { return totalPacientes; }
    public Long getTotalProfissionais() { return totalProfissionais; }
    public Long getInternacoesAtivas() { return internacoesAtivas; }
}
