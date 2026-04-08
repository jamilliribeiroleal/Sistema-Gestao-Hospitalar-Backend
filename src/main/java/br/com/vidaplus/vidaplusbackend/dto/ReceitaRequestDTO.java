package br.com.vidaplus.vidaplusbackend.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ReceitaRequestDTO {

    @NotNull
    private Long pacienteId;

    private Long profissionalId;

    private Long consultaId;

    @NotNull
    private LocalDateTime dataEmissao;

    @NotNull
    private String conteudo; 

    private String receitaPdfUrl;

    private String assinaturaDigital;
 
    public Long getPacienteId() {
        return pacienteId;
    }

    public Long getProfissionalId() {
        return profissionalId;
    }

    public Long getConsultaId() {
        return consultaId;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public String getReceitaPdfUrl() {
        return receitaPdfUrl;
    }

    public String getAssinaturaDigital() {
        return assinaturaDigital;
    }
}
