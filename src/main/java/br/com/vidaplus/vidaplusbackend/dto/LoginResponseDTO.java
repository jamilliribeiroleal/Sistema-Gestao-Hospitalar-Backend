package br.com.vidaplus.vidaplusbackend.dto;

import java.util.Set;

public class LoginResponseDTO {
    private String token;
    private String type;
    private String username;
    private Set<String> roles;

    public LoginResponseDTO() {
        this.type = "Bearer";
    }

    // Construtor com token 
    public LoginResponseDTO(String token) {
        this.token = token;
        this.type = "Bearer";
    }

    // Construtor com token e username
    public LoginResponseDTO(String token, String username) {
        this.token = token;
        this.username = username;
        this.type = "Bearer";
    }

    // Construtor completo (usado no AuthController)
    public LoginResponseDTO(String token, String type, String username, Set<String> roles) {
        this.token = token;
        this.type = type;
        this.username = username;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
