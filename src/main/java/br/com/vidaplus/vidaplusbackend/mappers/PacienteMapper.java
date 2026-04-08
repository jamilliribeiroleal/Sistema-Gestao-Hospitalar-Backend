package br.com.vidaplus.vidaplusbackend.mappers;

import br.com.vidaplus.vidaplusbackend.dto.PacienteDTO;
import br.com.vidaplus.vidaplusbackend.entities.Paciente;
import br.com.vidaplus.vidaplusbackend.enums.PacienteSexo;

public class PacienteMapper {

	private PacienteMapper() {

	}

// DTO → ENTITY
	public static Paciente toEntity(PacienteDTO dto) {
		if (dto == null)
			return null;

		Paciente paciente = new Paciente();

		paciente.setNome(dto.getNome());
		paciente.setDataNascimento(dto.getDataNascimento());

		if (dto.getSexo() != null) {
			paciente.setSexo(PacienteSexo.from(dto.getSexo()));
		}

		paciente.setEmail(dto.getEmail());
		paciente.setAtivo(dto.getAtivo());

		return paciente;
	}

// ENTITY → DTO
	public static PacienteDTO toDTO(Paciente paciente) {
		if (paciente == null)
			return null;

		return new PacienteDTO(paciente.getId(), paciente.getNome(), paciente.getDataNascimento(), paciente.getSexo(),
				paciente.getEmail(), paciente.getAtivo());
	}
}
