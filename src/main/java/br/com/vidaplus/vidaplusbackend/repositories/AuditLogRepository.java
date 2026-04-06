package br.com.vidaplus.vidaplusbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vidaplus.vidaplusbackend.entities.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {


}
