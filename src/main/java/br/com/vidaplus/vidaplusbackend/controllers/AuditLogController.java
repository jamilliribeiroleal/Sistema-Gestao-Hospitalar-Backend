package br.com.vidaplus.vidaplusbackend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.vidaplus.vidaplusbackend.dto.AuditLogRequestDTO;
import br.com.vidaplus.vidaplusbackend.dto.AuditLogResponseDTO;
import br.com.vidaplus.vidaplusbackend.entities.AuditLog;
import br.com.vidaplus.vidaplusbackend.services.AuditLogService;

@RestController
@RequestMapping("/api/audit-logs")
public class AuditLogController {

    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<AuditLogResponseDTO> salvar(
            @RequestBody AuditLogRequestDTO dto) {

        AuditLogResponseDTO response = auditLogService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // READ - ALL
    @GetMapping
    public ResponseEntity<List<AuditLogResponseDTO>> listarTodos() {
        return ResponseEntity.ok(auditLogService.listarTodos());
    }

    // READ - BY ID
    @GetMapping("/{id}")
    public ResponseEntity<AuditLogResponseDTO> buscarPorId(@PathVariable Long id) {
        return auditLogService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<AuditLogResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody AuditLogRequestDTO dto) {

        return auditLogService.atualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return auditLogService.deletar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
