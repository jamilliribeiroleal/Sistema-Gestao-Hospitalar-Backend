package br.com.vidaplus.vidaplusbackend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.vidaplus.vidaplusbackend.dto.InternacaoCreateDTO;
import br.com.vidaplus.vidaplusbackend.dto.InternacaoResponseDTO;
import br.com.vidaplus.vidaplusbackend.dto.InternacaoStatusUpdateDTO;
import br.com.vidaplus.vidaplusbackend.dto.InternacaoRequestDTO;
import br.com.vidaplus.vidaplusbackend.services.InternacaoService;

@RestController
@RequestMapping("/api/admin/internacoes")
@PreAuthorize("hasRole('ADMIN')")
public class InternacaoAdminController {

	private final InternacaoService service;

	public InternacaoAdminController(InternacaoService service) {
		this.service = service;
	}

//  LISTAR INTERNAÇÕES ATIVAS

	@GetMapping
	public List<InternacaoResponseDTO> listar() {
		return service.listarTodas();
	}

	@GetMapping("/ativas")
	public List<InternacaoResponseDTO> listarAtivas() {
		return service.listarAtivas();
	}

	// 🔎 BUSCAR POR ID
	@GetMapping("/{id}")
	public InternacaoResponseDTO buscar(@PathVariable Long id) {
		return service.buscarPorId(id);
	}

	// CRIAR INTERNAÇÃO
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public InternacaoResponseDTO criar(@RequestBody InternacaoCreateDTO dto) {

		InternacaoRequestDTO request = new InternacaoRequestDTO();
		request.setPacienteId(dto.getPacienteId());
		request.setProfissionalId(dto.getProfissionalId());
		request.setUnidadeId(dto.getUnidadeId());
		request.setMotivo(dto.getMotivo());

		return service.criar(request);
	}

	// 🔄 ATUALIZAR STATUS
	@PutMapping("/{id}")
	public InternacaoResponseDTO atualizarStatus(@PathVariable Long id, @RequestBody InternacaoStatusUpdateDTO dto) {

		InternacaoRequestDTO request = new InternacaoRequestDTO();
		request.setStatus(dto.getStatus());

		return service.atualizar(id, request);
	}

// 🏥 DAR ALTA
	@PutMapping("/{id}/alta")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void darAlta(@PathVariable Long id) {
		service.darAlta(id);
	}
}
