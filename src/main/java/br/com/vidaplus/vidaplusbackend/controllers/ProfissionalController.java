package br.com.vidaplus.vidaplusbackend.controllers;

import br.com.vidaplus.vidaplusbackend.dto.*;
import br.com.vidaplus.vidaplusbackend.services.ProfissionalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    // Listar profissionais ativos (admin)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<ProfissionalDTO>> listarAtivos() {
        return ResponseEntity.ok(profissionalService.findAllAtivos());
    }

    // Obter perfil do profissional logado
    @PreAuthorize("hasAuthority('ROLE_PROFISSIONAL')")
    @GetMapping("/me")
    public ResponseEntity<ProfissionalDTO> meuPerfil(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(profissionalService.getProfissionalLogado(username));
    }

    // Buscar profissional por ID (admin)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(profissionalService.getById(id));
    }

    // Criar novo profissional (admin)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ProfissionalDTO> criar(@Valid @RequestBody ProfissionalDTO dto) {
        ProfissionalDTO criado = profissionalService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    // Atualizar dados do profissional (admin)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProfissionalDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProfissionalDTO dto) {
        return ResponseEntity.ok(profissionalService.update(id, dto));
    }

    // Remover profissional (soft delete – admin)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        profissionalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Consultar agenda do profissional logado
    @PreAuthorize("hasAuthority('ROLE_PROFISSIONAL')")
    @GetMapping("/agenda")
    public ResponseEntity<List<AgendaDTO>> minhaAgenda(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(profissionalService.getAgendaDoUser(username));
    }

    // Agendar consulta pelo profissional logado
    @PreAuthorize("hasAuthority('ROLE_PROFISSIONAL')")
    @PostMapping("/consultas")
    public ResponseEntity<ConsultaResponseDTO> agendarConsulta(
            @Valid @RequestBody ConsultaRequestDTO dto,
            Authentication authentication) {

        String username = authentication.getName();
        ConsultaResponseDTO consulta = profissionalService.agendarConsulta(username, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(consulta);
    }

    // Cancelar consulta do profissional logado
    @PreAuthorize("hasAuthority('ROLE_PROFISSIONAL')")
    @PutMapping("/consultas/{consultaId}/cancelar")
    public ResponseEntity<Void> cancelarConsulta(
            @PathVariable Long consultaId,
            Authentication authentication) {

        String username = authentication.getName();
        profissionalService.cancelarConsulta(username, consultaId);
        return ResponseEntity.noContent().build();
    }

    // Atualizar prontuário pelo profissional logado
    @PreAuthorize("hasAuthority('ROLE_PROFISSIONAL')")
    @PutMapping("/prontuarios/{prontuarioId}")
    public ResponseEntity<ProntuarioResponseDTO> atualizarProntuario(
            @PathVariable Long prontuarioId,
            @Valid @RequestBody ProntuarioRequestDTO dto,
            Authentication authentication) {

        String username = authentication.getName();
        return ResponseEntity.ok(
                profissionalService.atualizarProntuarioPorUser(username, prontuarioId, dto)
        );
    }

    // Emitir receita digital pelo profissional logado
    @PreAuthorize("hasAuthority('ROLE_PROFISSIONAL')")
    @PostMapping("/receitas")
    public ResponseEntity<ReceitaDigitalDTO> emitirReceita(
            @Valid @RequestBody ReceitaDigitalDTO dto,
            Authentication authentication) {

        String username = authentication.getName();
        ReceitaDigitalDTO receita =
                profissionalService.emitirReceitaPorUser(username, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(receita);
    }

    // Consultar histórico do paciente (profissional logado)
    @PreAuthorize("hasAuthority('ROLE_PROFISSIONAL')")
    @GetMapping("/pacientes/{pacienteId}/historico")
    public ResponseEntity<List<ProntuarioResponseDTO>> historicoPaciente(
            @PathVariable Long pacienteId,
            Authentication authentication) {

        String username = authentication.getName();
        return ResponseEntity.ok(
                profissionalService.getHistoricoPacientePorUser(username, pacienteId)
        );
    }
}
