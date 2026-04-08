package br.com.vidaplus.vidaplusbackend.dto;

import java.time.LocalDate;

public class PacienteDTO {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String sexo;
    private String email;
    private Boolean ativo;

    public PacienteDTO() {}

    public PacienteDTO(Long id, String nome, LocalDate dataNascimento, Enum<?> sexo, String email, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = (sexo != null) ? sexo.name() : null;
        this.email = email;
        this.ativo = ativo;
    }

public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public void validarCamposObrigatorios() {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Campo 'nome' é obrigatório.");
        }
        if (dataNascimento == null) {
            throw new IllegalArgumentException("Campo 'dataNascimento' é obrigatório.");
        }
        if (sexo == null || sexo.isBlank()) {
            throw new IllegalArgumentException("Campo 'sexo' é obrigatório.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Campo 'email' é obrigatório.");
        }
    }

    @Override
    public String toString() {
        return "PacienteDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", sexo='" + sexo + '\'' +
                ", email='" + email + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}
