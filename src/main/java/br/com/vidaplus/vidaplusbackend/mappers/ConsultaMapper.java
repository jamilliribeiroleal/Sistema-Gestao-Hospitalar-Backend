package br.com.vidaplus.vidaplusbackend.mappers;

import br.com.vidaplus.vidaplusbackend.dto.ConsultaRequestDTO;
import br.com.vidaplus.vidaplusbackend.dto.ConsultaResponseDTO;
import br.com.vidaplus.vidaplusbackend.entities.Consulta;
import br.com.vidaplus.vidaplusbackend.entities.Paciente;
import br.com.vidaplus.vidaplusbackend.entities.Profissional;
import br.com.vidaplus.vidaplusbackend.entities.Unidade;
import br.com.vidaplus.vidaplusbackend.enums.StatusConsulta;
import br.com.vidaplus.vidaplusbackend.enums.TipoConsulta;

import java.time.LocalDateTime;
import java.util.UUID;

public class ConsultaMapper {

// REQUEST DTO -> ENTITY
public static Consulta toEntity(
            ConsultaRequestDTO dto,
            Paciente paciente,
            Profissional profissional,
            Unidade unidade
    ) {

        Consulta c = new Consulta();

        c.setPaciente(paciente);
        c.setProfissional(profissional);
        c.setUnidade(unidade);

        
        if (dto.getDataHora() == null || dto.getDataHora().isBlank()) {
            throw new IllegalArgumentException("Data e hora da consulta são obrigatórias");
        }

        try {
            c.setDataHora(LocalDateTime.parse(dto.getDataHora()));
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Formato inválido de dataHora. Use ISO-8601: yyyy-MM-ddTHH:mm:ss"
            );
        }

        c.setMotivo(dto.getMotivo());

        
        if (dto.getTipo() == null || dto.getTipo().isBlank()) {
            throw new IllegalArgumentException("Tipo da consulta é obrigatório");
        }

        try {
            TipoConsulta tipo =
                    TipoConsulta.valueOf(dto.getTipo().trim().toUpperCase());
            c.setTipo(tipo);

if (TipoConsulta.TELECONSULTA.equals(tipo)) {
                c.setLinkTeleconsulta(gerarLinkTeleconsulta());
            }

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Tipo de consulta inválido. Valores aceitos: PRESENCIAL ou TELECONSULTA"
            );
        }

        
        c.setStatus(StatusConsulta.AGENDADA);

        return c;
    }

	public static ConsultaResponseDTO toDTO(Consulta c) {

		ConsultaResponseDTO dto = new ConsultaResponseDTO();

		dto.setId(c.getId());

		dto.setPacienteId(c.getPaciente().getId());
		dto.setPacienteNome(c.getPaciente().getNome());

		if (c.getProfissional() != null) {
			dto.setProfissionalId(c.getProfissional().getId());
			dto.setProfissionalNome(c.getProfissional().getNome());
		}

		if (c.getUnidade() != null) {
			dto.setUnidadeId(c.getUnidade().getId());
			dto.setUnidadeNome(c.getUnidade().getNome());
		}

		dto.setDataHora(c.getDataHora() != null ? c.getDataHora().toString() : null);
		dto.setTipo(c.getTipo() != null ? c.getTipo().name() : null);
		dto.setStatus(c.getStatus());
		dto.setMotivo(c.getMotivo());
		dto.setLinkTeleconsulta(c.getLinkTeleconsulta());

		return dto;
	}

	private static String gerarLinkTeleconsulta() {
		return "https://teleconsulta.vidaplus.com/" + UUID.randomUUID();
	}
}
