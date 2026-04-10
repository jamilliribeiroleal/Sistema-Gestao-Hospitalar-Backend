package br.com.vidaplus.vidaplusbackend.controllers;

import br.com.vidaplus.vidaplusbackend.dto.ReceitaRequestDTO;
import br.com.vidaplus.vidaplusbackend.dto.ReceitaResponseDTO;
import br.com.vidaplus.vidaplusbackend.entities.Receita;
import br.com.vidaplus.vidaplusbackend.services.ReceitaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {

	private final ReceitaService receitaService;

	public ReceitaController(ReceitaService receitaService) {
		this.receitaService = receitaService;
	}

// LISTAR

	@GetMapping
	public ResponseEntity<List<ReceitaResponseDTO>> getAllReceitas() {
		return ResponseEntity.ok(receitaService.findAll().stream().map(ReceitaResponseDTO::fromEntity).toList());
	}

	// BUSCAR POR ID

	@GetMapping("/{id}")
	public ResponseEntity<ReceitaResponseDTO> getReceitaById(@PathVariable Long id) {
		return ResponseEntity.ok(ReceitaResponseDTO.fromEntity(receitaService.findById(id)));
	}

	// CRIAR

	@PostMapping
	public ResponseEntity<ReceitaResponseDTO> createReceita(@RequestBody @Valid ReceitaRequestDTO dto) {
		Receita receita = receitaService.save(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(ReceitaResponseDTO.fromEntity(receita));
	}

	// ATUALIZAR

	@PutMapping("/{id}")
	public ResponseEntity<ReceitaResponseDTO> updateReceita(@PathVariable Long id,
			@RequestBody @Valid ReceitaRequestDTO dto) {
		Receita receita = receitaService.update(id, dto);

		return ResponseEntity.ok(ReceitaResponseDTO.fromEntity(receita));
	}

	// REMOVER

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteReceita(@PathVariable Long id) {
		receitaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
