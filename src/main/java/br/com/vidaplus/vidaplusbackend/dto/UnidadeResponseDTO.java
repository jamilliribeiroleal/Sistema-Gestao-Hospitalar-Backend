package br.com.vidaplus.vidaplusbackend.dto;

import br.com.vidaplus.vidaplusbackend.entities.Unidade.UnidadeTipo;
import java.time.LocalDateTime;

public record UnidadeResponseDTO(
        Long id,
        String nome,
        UnidadeTipo tipo,
        String endereco,    
        String telefone,   
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
