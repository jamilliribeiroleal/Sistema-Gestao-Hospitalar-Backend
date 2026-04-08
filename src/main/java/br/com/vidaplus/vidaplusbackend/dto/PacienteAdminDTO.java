package br.com.vidaplus.vidaplusbackend.dto;

public class PacienteAdminDTO {

    private Long id;
    private String nome;
    private Boolean ativo;

    public PacienteAdminDTO(Long id, String nome, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.ativo = ativo;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Boolean getAtivo() { return ativo; }
}
