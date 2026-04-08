package br.com.vidaplus.vidaplusbackend.dto;

import br.com.vidaplus.vidaplusbackend.enums.LeitoStatus;
import br.com.vidaplus.vidaplusbackend.entities.Leito;

import java.time.LocalDateTime;

public record LeitoResponseDTO(
        Long id,
        Long unidadeId,
        String numero,
        LeitoStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static LeitoResponseDTO fromEntity(Leito leito) {
        return new LeitoResponseDTO(
                leito.getId(),
                leito.getUnidade().getId(), // 🔑 evita LazyInitialization
                leito.getNumero(),
                leito.getStatus(),
                leito.getCreatedAt(),
                leito.getUpdatedAt()
        );
    }
}
