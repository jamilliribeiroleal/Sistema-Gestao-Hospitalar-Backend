package br.com.vidaplus.vidaplusbackend.dto;

public class ProfissionalAdminDTO {

    private Long id;
    private String nome;
    private String especialidade;
    private Boolean ativo;

    public ProfissionalAdminDTO(Long id, String nome, String especialidade, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.ativo = ativo;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEspecialidade() { return especialidade; }
    public Boolean getAtivo() { return ativo; }
}
