package br.com.vidaplus.vidaplusbackend.controllers;

import br.com.vidaplus.vidaplusbackend.dto.LeitoResponseDTO;
import br.com.vidaplus.vidaplusbackend.entities.Leito;
import br.com.vidaplus.vidaplusbackend.services.LeitoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leitos")
public class LeitoController {

	private final LeitoService leitoService;

	public LeitoController(LeitoService leitoService) {
		this.leitoService = leitoService;
	}

	// LISTAR TODOS
	@GetMapping
	public ResponseEntity<List<LeitoResponseDTO>> listar() {
		return ResponseEntity.ok(leitoService.listar());
	}

	// BUSCAR POR ID
	@GetMapping("/{id}")
	public ResponseEntity<LeitoResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(leitoService.buscarPorId(id));
	}

	// CRIAR
	@PostMapping
	public ResponseEntity<LeitoResponseDTO> criar(@RequestBody @Valid Leito leito) {
		LeitoResponseDTO response = leitoService.salvar(leito);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// ATUALIZAR
	@PutMapping("/{id}")
	public ResponseEntity<LeitoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid Leito leito) {
		return ResponseEntity.ok(leitoService.atualizar(id, leito));
	}

	// DELETAR (SOFT DELETE)
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		leitoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
