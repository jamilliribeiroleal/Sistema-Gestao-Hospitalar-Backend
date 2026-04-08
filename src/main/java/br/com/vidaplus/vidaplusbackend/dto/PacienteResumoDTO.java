package br.com.vidaplus.vidaplusbackend.dto;

import br.com.vidaplus.vidaplusbackend.entities.Paciente;

public record PacienteResumoDTO(
        Long id,
        String nome,
        String cpf
) {
    public static PacienteResumoDTO fromEntity(Paciente paciente) {
        if (paciente == null) return null;

        String cpf = null;
        if (paciente.getCpfEnc() != null) {
            cpf = new String(paciente.getCpfEnc());
        }

        return new PacienteResumoDTO(
                paciente.getId(),
                paciente.getNome(),
                cpf
        );
    }
}
