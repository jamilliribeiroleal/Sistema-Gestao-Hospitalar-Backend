package br.com.vidaplus.vidaplusbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vidaplus.vidaplusbackend.entities.Notificacao;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    List<Notificacao> findByPacienteIdOrderByDataCriacaoDesc(Long pacienteId);

    long countByPacienteIdAndLidaFalse(Long pacienteId);
}
