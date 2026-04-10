package br.com.vidaplus.vidaplusbackend.controllers;

import br.com.vidaplus.vidaplusbackend.dto.ConsultaRequestDTO;
import br.com.vidaplus.vidaplusbackend.dto.ConsultaResponseDTO;
import br.com.vidaplus.vidaplusbackend.services.ConsultaService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

 // LISTAR TODAS
    @GetMapping
    public ResponseEntity<List<ConsultaResponseDTO>> listar() {
        return ResponseEntity.ok(consultaService.findAll());
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(consultaService.findById(id));
    }

    // AGENDAR CONSULTA ✅ CORRIGIDO
    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> agendarConsulta(
            @Valid @RequestBody ConsultaRequestDTO dto) {

        ConsultaResponseDTO response =
                consultaService.agendarConsulta(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // CANCELAR CONSULTA
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(
            @PathVariable Long id) {

        consultaService.cancelarConsulta(id);
        return ResponseEntity.noContent().build();
    }
}
