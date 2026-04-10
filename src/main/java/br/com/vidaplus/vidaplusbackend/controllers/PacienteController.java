package br.com.vidaplus.vidaplusbackend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vidaplus.vidaplusbackend.dto.ConsultaResponseDTO;
import br.com.vidaplus.vidaplusbackend.dto.PacienteCadastroDTO;
import br.com.vidaplus.vidaplusbackend.dto.PacienteDTO;
import br.com.vidaplus.vidaplusbackend.dto.ProntuarioResponseDTO;
import br.com.vidaplus.vidaplusbackend.entities.User;
import br.com.vidaplus.vidaplusbackend.services.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

	private final PacienteService pacienteService;

	public PacienteController(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}

//  PERFIL DO PACIENTE LOGADO
	@PreAuthorize("hasRole('PACIENTE')")
	@GetMapping("/me")
	public ResponseEntity<PacienteDTO> meuPerfil(Authentication authentication) {

		return ResponseEntity.ok(pacienteService.getPacienteLogado(authentication.getName()));
	}

//  CADASTRO DE PACIENTE
	@PostMapping("/cadastro")
	public ResponseEntity<PacienteDTO> cadastrarPaciente(@RequestBody PacienteCadastroDTO dto) {

		User user = pacienteService.registrarPaciente(dto);

		return ResponseEntity.ok(new PacienteDTO(user.getPaciente().getId(), user.getPaciente().getNome(),
				user.getPaciente().getDataNascimento(), user.getPaciente().getSexo(), user.getPaciente().getEmail(),
				user.getPaciente().getAtivo()));
	}

//  HISTÓRICO DO PACIENTE LOGADO
	@PreAuthorize("hasRole('PACIENTE')")
	@GetMapping("/me/historico")
	public ResponseEntity<List<ProntuarioResponseDTO>> meuHistorico(Authentication authentication) {

		PacienteDTO paciente = pacienteService.getPacienteLogado(authentication.getName());

		return ResponseEntity.ok(pacienteService.getHistoricoClinico(paciente.getId()));
	}

	// CONSULTAS DO PACIENTE LOGADO
	@PreAuthorize("hasRole('PACIENTE')")
	@GetMapping("/me/consultas")
	public ResponseEntity<List<ConsultaResponseDTO>> minhasConsultas(Authentication authentication) {

		PacienteDTO paciente = pacienteService.getPacienteLogado(authentication.getName());

		return ResponseEntity.ok(pacienteService.getConsultasDoPaciente(paciente.getId()));
	}

	// HISTÓRICO POR ID (ADMIN)
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}/historico")
	public ResponseEntity<List<ProntuarioResponseDTO>> historicoPorId(@PathVariable Long id) {

		return ResponseEntity.ok(pacienteService.getHistoricoClinico(id));
	}

	// CONSULTAS POR ID (ADMIN)
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}/consultas")
	public ResponseEntity<List<ConsultaResponseDTO>> consultasPorId(@PathVariable Long id) {

		return ResponseEntity.ok(pacienteService.getConsultasDoPaciente(id));
	}
}
