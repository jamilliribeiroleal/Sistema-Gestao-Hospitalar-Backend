package br.com.vidaplus.vidaplusbackend.dto;

import java.time.LocalDateTime;

public class ReceitaDigitalDTO {

    private Long profissionalId;
    private Long pacienteId;
    private String nomeProfissional;
    private String nomePaciente;
    private String conteudoReceita;
    private LocalDateTime dataEmissao;

    public ReceitaDigitalDTO() {}

    public ReceitaDigitalDTO(
            Long profissionalId,
            Long pacienteId,
            String nomeProfissional,
            String nomePaciente,
            String conteudoReceita,
            LocalDateTime dataEmissao) {

        this.profissionalId = profissionalId;
        this.pacienteId = pacienteId;
        this.nomeProfissional = nomeProfissional;
        this.nomePaciente = nomePaciente;
        this.conteudoReceita = conteudoReceita;
        this.dataEmissao = dataEmissao;
    }

    public Long getProfissionalId() {
        return profissionalId;
    }

    public void setProfissionalId(Long profissionalId) {
        this.profissionalId = profissionalId;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getConteudoReceita() {
        return conteudoReceita;
    }

    public void setConteudoReceita(String conteudoReceita) {
        this.conteudoReceita = conteudoReceita;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDateTime dataEmissao) {
        this.dataEmissao = dataEmissao;
    }
}
