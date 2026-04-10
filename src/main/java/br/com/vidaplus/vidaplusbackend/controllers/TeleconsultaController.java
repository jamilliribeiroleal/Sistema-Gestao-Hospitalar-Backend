package br.com.vidaplus.vidaplusbackend.controllers;

import br.com.vidaplus.vidaplusbackend.dto.TeleconsultaRequestDTO;
import br.com.vidaplus.vidaplusbackend.dto.TeleconsultaResponseDTO;
import br.com.vidaplus.vidaplusbackend.entities.Teleconsulta;
import br.com.vidaplus.vidaplusbackend.mappers.TeleconsultaMapper;
import br.com.vidaplus.vidaplusbackend.services.TeleconsultaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/teleconsultas")
public class TeleconsultaController {

	private final TeleconsultaService teleconsultaService;

	public TeleconsultaController(TeleconsultaService teleconsultaService) {
		this.teleconsultaService = teleconsultaService;
	}

	// LISTAR TODAS
	@GetMapping
	public ResponseEntity<List<TeleconsultaResponseDTO>> getAll() {
		List<Teleconsulta> teleconsultas = teleconsultaService.findAll();
		List<TeleconsultaResponseDTO> dtos = TeleconsultaMapper.toResponseDTOList(teleconsultas);
		return ResponseEntity.ok(dtos);
	}

//  BUSCAR POR ID
	@GetMapping("/{id}")
	public ResponseEntity<TeleconsultaResponseDTO> getById(@PathVariable Long id) {
		Teleconsulta teleconsulta = teleconsultaService.findById(id);
		return ResponseEntity.ok(TeleconsultaMapper.toResponseDTO(teleconsulta));
	}

	// CRIAR TELECONSULTA
	@PostMapping
	public ResponseEntity<TeleconsultaResponseDTO> criar(@RequestBody @Valid TeleconsultaRequestDTO dto) {

		Teleconsulta teleconsulta = teleconsultaService.criarTeleconsulta(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(TeleconsultaMapper.toResponseDTO(teleconsulta));
	}

	// ATUALIZAR STATUS DA CHAMADA
	@PutMapping("/{id}/status")
	public ResponseEntity<TeleconsultaResponseDTO> atualizarStatus(@PathVariable Long id,
			@RequestParam Teleconsulta.StatusChamada status) {

		Teleconsulta teleconsulta = teleconsultaService.atualizarStatus(id, status);
		return ResponseEntity.ok(TeleconsultaMapper.toResponseDTO(teleconsulta));
	}

	// CANCELAR TELECONSULTA
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> cancelar(@PathVariable Long id) {
		teleconsultaService.cancelar(id);
		return ResponseEntity.noContent().build();
	}
}
