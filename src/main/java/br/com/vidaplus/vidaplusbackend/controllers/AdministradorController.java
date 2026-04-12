package br.com.vidaplus.vidaplusbackend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.vidaplus.vidaplusbackend.dto.InternacaoAdminDTO;
import br.com.vidaplus.vidaplusbackend.dto.InternacaoCreateDTO;
import br.com.vidaplus.vidaplusbackend.dto.InternacaoStatusUpdateDTO;
import br.com.vidaplus.vidaplusbackend.dto.PacienteAdminDTO;
import br.com.vidaplus.vidaplusbackend.dto.PacienteCreateDTO;
import br.com.vidaplus.vidaplusbackend.dto.PacienteUpdateDTO;
import br.com.vidaplus.vidaplusbackend.dto.ProfissionalAdminDTO;
import br.com.vidaplus.vidaplusbackend.dto.ProfissionalCreateDTO;
import br.com.vidaplus.vidaplusbackend.dto.ProfissionalUpdateDTO;
import br.com.vidaplus.vidaplusbackend.dto.RelatorioResumoDTO;
import br.com.vidaplus.vidaplusbackend.services.AdministradorService;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdministradorController {

    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    //  PACIENTES

    @GetMapping("/pacientes")
    public ResponseEntity<List<PacienteAdminDTO>> listarPacientes() {
        return ResponseEntity.ok(administradorService.listarPacientesDTO());
    }

    @GetMapping("/pacientes/{id}")
    public ResponseEntity<PacienteAdminDTO> buscarPacientePorId(@PathVariable Long id) {
        return ResponseEntity.ok(administradorService.buscarPacientePorId(id));
    }

    @PostMapping("/pacientes")
    public ResponseEntity<PacienteAdminDTO> criarPaciente(
            @RequestBody PacienteCreateDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(administradorService.criarPacienteAdminDTO(dto));
    }

    @PutMapping("/pacientes/{id}")
    public ResponseEntity<PacienteAdminDTO> atualizarPaciente(
            @PathVariable Long id,
            @RequestBody PacienteUpdateDTO dto) {

        return ResponseEntity.ok(
                administradorService.atualizarPacienteAdminDTO(id, dto)
        );
    }

    @DeleteMapping("/pacientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPaciente(@PathVariable Long id) {
        administradorService.deletarPaciente(id);
    }


    //  PROFISSIONAIS

    @GetMapping("/profissionais")
    public ResponseEntity<List<ProfissionalAdminDTO>> listarProfissionais() {
        return ResponseEntity.ok(administradorService.listarProfissionaisDTO());
    }

    @GetMapping("/profissionais/{id}")
    public ResponseEntity<ProfissionalAdminDTO> buscarProfissionalPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                administradorService.buscarProfissionalPorId(id)
        );
    }

    @PostMapping("/profissionais")
    public ResponseEntity<ProfissionalAdminDTO> criarProfissional(
            @RequestBody ProfissionalCreateDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(administradorService.criarProfissionalAdminDTO(dto));
    }

    @PutMapping("/profissionais/{id}")
    public ResponseEntity<ProfissionalAdminDTO> atualizarProfissional(
            @PathVariable Long id,
            @RequestBody ProfissionalUpdateDTO dto) {

        return ResponseEntity.ok(
                administradorService.atualizarProfissionalAdminDTO(id, dto)
        );
    }

    @DeleteMapping("/profissionais/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProfissional(@PathVariable Long id) {
        administradorService.deletarProfissional(id);
    }


    //  RELATÓRIOS

    @GetMapping("/relatorios/resumo")
    public ResponseEntity<RelatorioResumoDTO> resumo() {
        return ResponseEntity.ok(
                new RelatorioResumoDTO(
                        administradorService.totalPacientes(),
                        administradorService.totalProfissionais(),
                        administradorService.internacoesAtivas()
                )
        );
    }
}
  