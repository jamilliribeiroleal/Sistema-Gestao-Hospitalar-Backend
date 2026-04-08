package br.com.vidaplus.vidaplusbackend.dto;

import java.time.LocalDateTime;

public record AdminProfissionalDTO(
        Long id,
        String nome,
        String especialidade,
        String email,
        Boolean ativo,
        LocalDateTime createdAt
) {
}
