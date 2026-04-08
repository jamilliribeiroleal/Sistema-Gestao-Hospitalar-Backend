package br.com.vidaplus.vidaplusbackend.mappers;

import br.com.vidaplus.vidaplusbackend.dto.AuditLogRequestDTO;
import br.com.vidaplus.vidaplusbackend.dto.AuditLogResponseDTO;
import br.com.vidaplus.vidaplusbackend.entities.AuditLog;
import br.com.vidaplus.vidaplusbackend.entities.User;

public class AuditLogMapper {

	private AuditLogMapper() {

	}

// REQUEST DTO -> ENTITY
	public static AuditLog toEntity(AuditLogRequestDTO dto) {

		if (dto == null)
			return null;

		AuditLog audit = new AuditLog();

		audit.setEntidade(dto.getEntidade());
		audit.setEntidadeId(dto.getEntidadeId());
		audit.setAcao(dto.getAcao());
		audit.setDetalhes(dto.getDetalhes());
		audit.setIp(dto.getIp());
		audit.setCorrelationId(dto.getCorrelationId());

		if (dto.getUserId() != null) {
			User user = new User();
			user.setId(dto.getUserId());
			audit.setUser(user);
		}

		return audit;
	}

	public static AuditLogResponseDTO toResponseDTO(AuditLog audit) {

		if (audit == null)
			return null;

		AuditLogResponseDTO dto = new AuditLogResponseDTO();

		dto.setId(audit.getId());
		dto.setEntidade(audit.getEntidade());
		dto.setEntidadeId(audit.getEntidadeId());
		dto.setAcao(audit.getAcao());
		dto.setDetalhes(audit.getDetalhes());
		dto.setIp(audit.getIp());
		dto.setCorrelationId(audit.getCorrelationId());
		dto.setCreatedAt(audit.getCreatedAt());

		if (audit.getUser() != null) {
			dto.setUserId(audit.getUser().getId());
		}

		return dto;
	}
}
