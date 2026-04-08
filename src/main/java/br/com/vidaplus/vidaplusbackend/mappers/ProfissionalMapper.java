package br.com.vidaplus.vidaplusbackend.mappers;

import br.com.vidaplus.vidaplusbackend.dto.ProfissionalDTO;
import br.com.vidaplus.vidaplusbackend.dto.ProfissionalResumoDTO;
import br.com.vidaplus.vidaplusbackend.entities.Profissional;

public class ProfissionalMapper {

	public static ProfissionalDTO toDTO(Profissional profissional) {
		if (profissional == null)
			return null;

		ProfissionalDTO dto = new ProfissionalDTO();
		dto.setId(profissional.getId());
		dto.setNome(profissional.getNome());
		dto.setEmail(profissional.getEmail());
		dto.setCrm(profissional.getRegistroProfissional()); // compatível com getCrm/setCrm
		dto.setEspecialidade(profissional.getEspecialidade());
		dto.setAtivo(profissional.getAtivo());

		return dto;
	}

	public static ProfissionalResumoDTO toResumoDTO(Profissional profissional) {
		if (profissional == null)
			return null;
		return ProfissionalResumoDTO.fromEntity(profissional);
	}

	public static Profissional toEntity(ProfissionalDTO dto) {
		if (dto == null)
			return null;

		Profissional profissional = new Profissional();
		profissional.setId(dto.getId());
		profissional.setNome(dto.getNome());
		profissional.setEmail(dto.getEmail());
		profissional.setRegistroProfissional(dto.getCrm()); // compatível com getCrm/setCrm
		profissional.setEspecialidade(dto.getEspecialidade());
		profissional.setAtivo(dto.getAtivo() != null ? dto.getAtivo() : true);

		return profissional;
	}
}
