package br.com.vidaplus.vidaplusbackend.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vidaplus.vidaplusbackend.dto.NotificacaoDTO;
import br.com.vidaplus.vidaplusbackend.entities.Notificacao;
import br.com.vidaplus.vidaplusbackend.entities.Paciente;
import br.com.vidaplus.vidaplusbackend.exceptions.ResourceNotFoundException;
import br.com.vidaplus.vidaplusbackend.repositories.NotificacaoRepository;
import br.com.vidaplus.vidaplusbackend.repositories.PacienteRepository;

@Service
public class NotificacaoService {

	private final NotificacaoRepository notificacaoRepository;
	private final PacienteRepository pacienteRepository;

	public NotificacaoService(NotificacaoRepository notificacaoRepository, PacienteRepository pacienteRepository) {

		this.notificacaoRepository = notificacaoRepository;
		this.pacienteRepository = pacienteRepository;
	}

// BUSCAR NOTIFICAÇÕES DO PACIENTE

	@Transactional(readOnly = true)
	public List<NotificacaoDTO> getByPaciente(Long pacienteId) {

		pacienteRepository.findById(pacienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + pacienteId));

		return notificacaoRepository.findByPacienteIdOrderByDataCriacaoDesc(pacienteId).stream()
				.map(n -> new NotificacaoDTO(n.getId(), n.getTitulo(), n.getMensagem(), n.isLida(), n.getDataCriacao()))
				.toList();
	}

// CRIAR NOTIFICAÇÃO (USO INTERNO)

	@Transactional
	public void criarNotificacao(Paciente paciente, String titulo, String mensagem) {

		Notificacao notificacao = new Notificacao(paciente, titulo, mensagem);
		notificacaoRepository.save(notificacao);
	}

// MARCAR COMO LIDA

	@Transactional
	public void marcarComoLida(Long notificacaoId) {

		Notificacao notificacao = notificacaoRepository.findById(notificacaoId)
				.orElseThrow(() -> new ResourceNotFoundException("Notificação não encontrada"));

		notificacao.marcarComoLida();
	}

// CONTAR NÃO LIDAS

	@Transactional(readOnly = true)
	public long countNaoLidas(Long pacienteId) {
		return notificacaoRepository.countByPacienteIdAndLidaFalse(pacienteId);
	}
}
