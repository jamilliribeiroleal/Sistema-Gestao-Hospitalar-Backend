package br.com.vidaplus.vidaplusbackend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.vidaplus.vidaplusbackend.dto.AuditLogRequestDTO;
import br.com.vidaplus.vidaplusbackend.dto.AuditLogResponseDTO;
import br.com.vidaplus.vidaplusbackend.entities.AuditLog;
import br.com.vidaplus.vidaplusbackend.entities.User;
import br.com.vidaplus.vidaplusbackend.mappers.AuditLogMapper;
import br.com.vidaplus.vidaplusbackend.repositories.AuditLogRepository;
import br.com.vidaplus.vidaplusbackend.repositories.UserRepository;

@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;
    private final UserRepository userRepository;

    public AuditLogService(AuditLogRepository auditLogRepository,
                           UserRepository userRepository) {
        this.auditLogRepository = auditLogRepository;
        this.userRepository = userRepository;
    }


    public AuditLogResponseDTO salvar(AuditLogRequestDTO dto) {

        AuditLog auditLog = AuditLogMapper.toEntity(dto);

        // Garantir data de criação
        auditLog.setCreatedAt(LocalDateTime.now());

        // Resolver usuário se vier apenas com ID
        if (dto.getUserId() != null) {
            Optional<User> user = userRepository.findById(dto.getUserId());
            user.ifPresent(auditLog::setUser);
        } else {
            auditLog.setUser(null);
        }

        AuditLog salvo = auditLogRepository.save(auditLog);
        return AuditLogMapper.toResponseDTO(salvo);
    }


    public List<AuditLogResponseDTO> listarTodos() {
        return auditLogRepository.findAll()
                .stream()
                .map(AuditLogMapper::toResponseDTO)
                .toList();
    }


    public Optional<AuditLogResponseDTO> buscarPorId(Long id) {
        return auditLogRepository.findById(id)
                .map(AuditLogMapper::toResponseDTO);
    }


    public Optional<AuditLogResponseDTO> atualizar(Long id, AuditLogRequestDTO dto) {

        return auditLogRepository.findById(id).map(audit -> {

            audit.setEntidade(dto.getEntidade());
            audit.setEntidadeId(dto.getEntidadeId());
            audit.setAcao(dto.getAcao());
            audit.setDetalhes(dto.getDetalhes());
            audit.setIp(dto.getIp());
            audit.setCorrelationId(dto.getCorrelationId());

            if (dto.getUserId() != null) {
                userRepository.findById(dto.getUserId())
                        .ifPresent(audit::setUser);
            } else {
                audit.setUser(null);
            }

            AuditLog atualizado = auditLogRepository.save(audit);
            return AuditLogMapper.toResponseDTO(atualizado);
        });
    }

    public boolean deletar(Long id) {
        if (!auditLogRepository.existsById(id)) {
            return false;
        }
        auditLogRepository.deleteById(id);
        return true;
    }
}
