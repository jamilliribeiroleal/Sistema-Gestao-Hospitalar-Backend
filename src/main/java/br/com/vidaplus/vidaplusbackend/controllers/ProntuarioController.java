package br.com.vidaplus.vidaplusbackend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.vidaplus.vidaplusbackend.dto.ProntuarioCreateDTO;
import br.com.vidaplus.vidaplusbackend.dto.ProntuarioRequestDTO;
import br.com.vidaplus.vidaplusbackend.dto.ProntuarioResponseDTO;
import br.com.vidaplus.vidaplusbackend.services.ProntuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/prontuarios")
public class ProntuarioController {

	private final ProntuarioService prontuarioService;

	public ProntuarioController(ProntuarioService prontuarioService) {
		this.prontuarioService = prontuarioService;
	}

	// LISTAR
	@GetMapping
	public ResponseEntity<List<ProntuarioResponseDTO>> listarTodos() {
		return ResponseEntity.ok(prontuarioService.findAll());
	}

	// BUSCAR POR ID
	@GetMapping("/{id}")
	public ResponseEntity<ProntuarioResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(prontuarioService.findByIdDTO(id));
	}

// CRIAR
	@PostMapping
	public ResponseEntity<ProntuarioResponseDTO> salvar(@RequestBody @Valid ProntuarioCreateDTO dto) {
		return ResponseEntity.ok(prontuarioService.save(dto));
	}

	// ATUALIZAR
	@PutMapping("/{id}")
	public ResponseEntity<ProntuarioResponseDTO> atualizar(@PathVariable Long id,
			@RequestBody @Valid ProntuarioRequestDTO dto) {
		return ResponseEntity.ok(prontuarioService.update(id, dto));
	}

	// REMOVER (SOFT DELETE)
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		prontuarioService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
