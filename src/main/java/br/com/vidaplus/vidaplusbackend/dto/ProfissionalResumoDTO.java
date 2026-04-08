package br.com.vidaplus.vidaplusbackend.dto;

import br.com.vidaplus.vidaplusbackend.entities.Profissional;

public record ProfissionalResumoDTO(
        Long id,
        String nome,
        String registroProfissional,
        String especialidade
) {
    public static ProfissionalResumoDTO fromEntity(Profissional profissional) {
        if (profissional == null) return null;

        return new ProfissionalResumoDTO(
                profissional.getId(),
                profissional.getNome(),
                profissional.getRegistroProfissional(),
                profissional.getEspecialidade() // apenas campos simples
        );
    }
}
