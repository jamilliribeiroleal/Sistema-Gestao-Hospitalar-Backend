package br.com.vidaplus.vidaplusbackend.mappers;

import br.com.vidaplus.vidaplusbackend.dto.InternacaoResponseDTO;
import br.com.vidaplus.vidaplusbackend.entities.Internacao;

public class InternacaoMapper {

    public static InternacaoResponseDTO toDTO(Internacao i) {

        InternacaoResponseDTO dto = new InternacaoResponseDTO();

        dto.setId(i.getId());

        dto.setPacienteId(i.getPaciente().getId());
        dto.setPacienteNome(i.getPaciente().getNome());

        dto.setProfissionalId(i.getProfissionalResponsavel().getId());
        dto.setProfissionalNome(i.getProfissionalResponsavel().getNome());

        dto.setUnidadeId(i.getUnidade().getId());
        dto.setUnidadeNome(i.getUnidade().getNome());

        dto.setDataEntrada(i.getDataEntrada());
        dto.setDataAlta(i.getDataAlta());

        dto.setStatus(i.getStatus().name());

        dto.setMotivo(i.getMotivo());

        return dto;
    }
}
