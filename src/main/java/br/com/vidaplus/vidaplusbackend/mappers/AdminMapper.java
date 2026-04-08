package br.com.vidaplus.vidaplusbackend.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.vidaplus.vidaplusbackend.dto.AdminPacienteDTO;
import br.com.vidaplus.vidaplusbackend.dto.AdminProfissionalDTO;
import br.com.vidaplus.vidaplusbackend.entities.Paciente;
import br.com.vidaplus.vidaplusbackend.entities.Profissional;

public final class AdminMapper {

	private AdminMapper() {

	}

	//  PACIENTE → DTO ADMIN

	public static AdminPacienteDTO toPacienteDTO(Paciente paciente) {
		if (paciente == null)
			return null;

		return new AdminPacienteDTO(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getAtivo(),
				paciente.getCreatedAt());
	}

	public static List<AdminPacienteDTO> toPacienteDTOList(List<Paciente> pacientes) {
		return pacientes.stream().map(AdminMapper::toPacienteDTO).collect(Collectors.toList());
	}

//  PROFISSIONAL → DTO ADMIN

	public static AdminProfissionalDTO toProfissionalDTO(Profissional profissional) {
		if (profissional == null)
			return null;

		return new AdminProfissionalDTO(profissional.getId(), profissional.getNome(), profissional.getEspecialidade(),
				profissional.getEmail(), profissional.getAtivo(), profissional.getCreatedAt());
	}

	public static List<AdminProfissionalDTO> toProfissionalDTOList(List<Profissional> profissionais) {

		return profissionais.stream().map(AdminMapper::toProfissionalDTO).collect(Collectors.toList());
	}
}
