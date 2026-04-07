package br.com.vidaplus.vidaplusbackend.dto;

import java.util.Set;

public class UsuarioLogadoDTO {

    private Long id;
    private String username;
    private String email;
    private Set<String> roles;

    private Long pacienteId;
    private Long profissionalId;

    public UsuarioLogadoDTO(Long id,
                             String username,
                             String email,
                             Set<String> roles,
                             Long pacienteId,
                             Long profissionalId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.pacienteId = pacienteId;
        this.profissionalId = profissionalId;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public Set<String> getRoles() { return roles; }
    public Long getPacienteId() { return pacienteId; }
    public Long getProfissionalId() { return profissionalId; }
}
