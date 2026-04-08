package br.com.vidaplus.vidaplusbackend.mappers;

import br.com.vidaplus.vidaplusbackend.dto.TeleconsultaRequestDTO;
import br.com.vidaplus.vidaplusbackend.dto.TeleconsultaResponseDTO;
import br.com.vidaplus.vidaplusbackend.dto.PacienteResumoDTO;
import br.com.vidaplus.vidaplusbackend.dto.ProfissionalResumoDTO;
import br.com.vidaplus.vidaplusbackend.entities.Teleconsulta;
import br.com.vidaplus.vidaplusbackend.entities.Paciente;
import br.com.vidaplus.vidaplusbackend.entities.Profissional;
import br.com.vidaplus.vidaplusbackend.entities.Consulta;
import br.com.vidaplus.vidaplusbackend.repositories.PacienteRepository;
import br.com.vidaplus.vidaplusbackend.repositories.ProfissionalRepository;
import br.com.vidaplus.vidaplusbackend.repositories.ConsultaRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TeleconsultaMapper {

	// Converte DTO de requisição para entidade
	public static Teleconsulta toEntity(TeleconsultaRequestDTO dto, PacienteRepository pacienteRepo,
			ProfissionalRepository profissionalRepo, ConsultaRepository consultaRepo) {
		if (dto == null)
			return null;

		Teleconsulta teleconsulta = new Teleconsulta();

		// Buscar entidades completas pelo ID
		Paciente paciente = pacienteRepo.findById(dto.getPacienteId()).orElseThrow(
				() -> new IllegalArgumentException("Paciente não encontrado com id: " + dto.getPacienteId()));
		Profissional profissional = profissionalRepo.findById(dto.getProfissionalId()).orElseThrow(
				() -> new IllegalArgumentException("Profissional não encontrado com id: " + dto.getProfissionalId()));

		teleconsulta.setPaciente(paciente);
		teleconsulta.setProfissional(profissional);

		if (dto.getConsultaId() != null) {
			Consulta consulta = consultaRepo.findById(dto.getConsultaId()).orElseThrow(
					() -> new IllegalArgumentException("Consulta não encontrada com id: " + dto.getConsultaId()));
			teleconsulta.setConsulta(consulta);
		}

		teleconsulta.setLinkAcesso(dto.getLinkAcesso());
		teleconsulta.setStatusChamada(dto.getStatusChamada());

		return teleconsulta;
	}

// Converte entidade para DTO de resposta
	public static TeleconsultaResponseDTO toResponseDTO(Teleconsulta entity) {
		if (entity == null)
			return null;

		TeleconsultaResponseDTO dto = new TeleconsultaResponseDTO();
		dto.setId(entity.getId());

		if (entity.getPaciente() != null) {
			dto.setPaciente(PacienteResumoDTO.fromEntity(entity.getPaciente()));
		}

		if (entity.getProfissional() != null) {
			dto.setProfissional(ProfissionalResumoDTO.fromEntity(entity.getProfissional()));
		}

		if (entity.getConsulta() != null) {
			dto.setConsultaId(entity.getConsulta().getId());
		}

		dto.setLinkAcesso(entity.getLinkAcesso());
		dto.setStatusChamada(entity.getStatusChamada());
		dto.setDataInicio(entity.getDataInicio());
		dto.setDataFim(entity.getDataFim());

		return dto;
	}

	// Converte lista de entidades para lista de DTOs
	public static List<TeleconsultaResponseDTO> toResponseDTOList(List<Teleconsulta> entities) {
		if (entities == null)
			return null;
		return entities.stream().map(TeleconsultaMapper::toResponseDTO).collect(Collectors.toList());
	}

// Converte lista de DTOs para lista de entidades
	public static List<Teleconsulta> toEntityList(List<TeleconsultaRequestDTO> dtos, PacienteRepository pacienteRepo,
			ProfissionalRepository profissionalRepo, ConsultaRepository consultaRepo) {
		if (dtos == null)
			return null;
		return dtos.stream().map(dto -> toEntity(dto, pacienteRepo, profissionalRepo, consultaRepo))
				.collect(Collectors.toList());
	}
}
