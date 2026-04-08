package br.com.vidaplus.vidaplusbackend.dto;

import br.com.vidaplus.vidaplusbackend.entities.Profissional;

public class ProfissionalDTO {

    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String especialidade;
    private Boolean ativo;

    public ProfissionalDTO() {}

    public ProfissionalDTO(Long id, String nome, String email, String crm, String especialidade, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.crm = crm;
        this.especialidade = especialidade;
        this.ativo = ativo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCrm() { return crm; }
    public void setCrm(String crm) { this.crm = crm; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    // Conversão a partir da entidade
    public static ProfissionalDTO fromEntity(Profissional profissional) {
        if (profissional == null) return null;
        return new ProfissionalDTO(
                profissional.getId(),
                profissional.getNome(),
                profissional.getEmail(),
                profissional.getRegistroProfissional(),
                profissional.getEspecialidade(),
                profissional.getAtivo()
        );
    }

    // Conversão para entidade
    public Profissional toEntity() {
        Profissional profissional = new Profissional();
        profissional.setId(this.id);
        profissional.setNome(this.nome);
        profissional.setEmail(this.email);
        profissional.setRegistroProfissional(this.crm);
        profissional.setEspecialidade(this.especialidade);
        profissional.setAtivo(this.ativo != null ? this.ativo : true);
        return profissional;
    }
}
