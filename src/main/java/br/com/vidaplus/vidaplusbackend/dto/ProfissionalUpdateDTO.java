package br.com.vidaplus.vidaplusbackend.dto;

public class ProfissionalUpdateDTO {

    private String nome;
    private String especialidade;
    private String email;
    private String telefone;
    private Boolean ativo;

    public String getNome() { return nome; }
    public String getEspecialidade() { return especialidade; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public Boolean getAtivo() { return ativo; }
}
