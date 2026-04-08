package br.com.vidaplus.vidaplusbackend.mappers;

import org.springframework.stereotype.Component;

import br.com.vidaplus.vidaplusbackend.dto.ProntuarioResponseDTO;
import br.com.vidaplus.vidaplusbackend.entities.Prontuario;

@Component
public class ProntuarioMapper {

    public ProntuarioResponseDTO toDTO(
            Prontuario prontuario,
            String descricao,
            String diagnostico,
            String observacoes
    ) {

        ProntuarioResponseDTO dto = new ProntuarioResponseDTO();

        dto.setId(prontuario.getId());
        dto.setPacienteId(
                prontuario.getPaciente() != null
                        ? prontuario.getPaciente().getId()
                        : null
        );

        if (prontuario.getProfissional() != null) {
            dto.setProfissionalId(prontuario.getProfissional().getId());
            dto.setProfissionalNome(prontuario.getProfissional().getNome());
        }

        if (prontuario.getConsulta() != null) {
            dto.setConsultaId(prontuario.getConsulta().getId());
        }

        dto.setDescricao(descricao);
        dto.setDiagnostico(diagnostico);
        dto.setObservacoes(observacoes);

dto.setCreatedAt(prontuario.getCreatedAt());

        return dto;
    }
}
