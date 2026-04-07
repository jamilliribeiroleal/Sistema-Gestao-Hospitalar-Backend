package br.com.vidaplus.vidaplusbackend.dto;

import java.time.LocalDate;

public class PacienteUpdateDTO {

    private String nome;
    private LocalDate dataNascimento;
    private String sexo;
    private String email;
    private String telefone;
    private Boolean ativo;

    public String getNome() { return nome; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public String getSexo() { return sexo; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public Boolean getAtivo() { return ativo; }
}
