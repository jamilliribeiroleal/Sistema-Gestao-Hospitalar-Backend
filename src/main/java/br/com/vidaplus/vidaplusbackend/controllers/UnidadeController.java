package br.com.vidaplus.vidaplusbackend.controllers;

import br.com.vidaplus.vidaplusbackend.dto.UnidadeResponseDTO;
import br.com.vidaplus.vidaplusbackend.entities.Unidade;
import br.com.vidaplus.vidaplusbackend.services.UnidadeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidades")
public class UnidadeController {

	private final UnidadeService unidadeService;

	public UnidadeController(UnidadeService unidadeService) {
		this.unidadeService = unidadeService;
	}

// LISTAR TODAS (DTO)
	@GetMapping
	public ResponseEntity<List<UnidadeResponseDTO>> listarTodas() {
		return ResponseEntity.ok(unidadeService.listar());
	}

	// BUSCAR POR ID (DTO)
	@GetMapping("/{id}")
	public ResponseEntity<UnidadeResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(unidadeService.buscarPorId(id));
	}

	// CRIAR
	@PostMapping
	public ResponseEntity<UnidadeResponseDTO> salvar(@RequestBody @Valid Unidade unidade) {
		return ResponseEntity.ok(unidadeService.salvar(unidade));
	}

	// ATUALIZAR
	@PutMapping("/{id}")
	public ResponseEntity<UnidadeResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid Unidade unidade) {
		return ResponseEntity.ok(unidadeService.atualizar(id, unidade));
	}

	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		unidadeService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
