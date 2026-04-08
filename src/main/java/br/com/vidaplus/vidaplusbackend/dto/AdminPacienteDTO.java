package br.com.vidaplus.vidaplusbackend.dto;

import java.time.LocalDateTime;

public record AdminPacienteDTO(
        Long id,
        String nome,
        String email,
        Boolean ativo,
        LocalDateTime createdAt
) {
}
