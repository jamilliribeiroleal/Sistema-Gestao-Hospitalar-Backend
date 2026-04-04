package br.com.vidaplus.vidaplusbackend.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "profissionais")
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "registro_profissional", length = 100)
    private String registroProfissional;

    @Column(length = 150)
    private String especialidade;

    @Column
    private String email;

    @Column(name = "telefone_enc")
    private byte[] telefoneEnc;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consulta> consultas = new ArrayList<>();

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prontuario> prontuarios = new ArrayList<>();

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receita> receitas = new ArrayList<>();

    public Profissional() {}

    public Profissional(Long id, String nome, String registroProfissional, String especialidade, String email,
                        byte[] telefoneEnc, Boolean ativo, LocalDateTime createdAt,
                        LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.id = id;
        this.nome = nome;
        this.registroProfissional = registroProfissional;
        this.especialidade = especialidade;
        this.email = email;
        this.telefoneEnc = telefoneEnc;
        this.ativo = ativo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getRegistroProfissional() { return registroProfissional; }
    public void setRegistroProfissional(String registroProfissional) { this.registroProfissional = registroProfissional; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public byte[] getTelefoneEnc() { return telefoneEnc; }
    public void setTelefoneEnc(byte[] telefoneEnc) { this.telefoneEnc = telefoneEnc; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public LocalDateTime getDeletedAt() { return deletedAt; }
    public void setDeletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; }

    public List<Consulta> getConsultas() { return Collections.unmodifiableList(consultas); }
    public void setConsultas(List<Consulta> consultas) { this.consultas = consultas; }

    public List<Prontuario> getProntuarios() { return Collections.unmodifiableList(prontuarios); }
    public void setProntuarios(List<Prontuario> prontuarios) { this.prontuarios = prontuarios; }

    public List<Receita> getReceitas() { return Collections.unmodifiableList(receitas); }
    public void setReceitas(List<Receita> receitas) { this.receitas = receitas; }

    @Override
    public String toString() {
        return "Profissional{id=" + id + ", nome='" + nome + "'}";
    }
}
