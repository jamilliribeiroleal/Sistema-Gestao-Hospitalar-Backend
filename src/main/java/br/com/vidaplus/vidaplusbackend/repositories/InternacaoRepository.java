package br.com.vidaplus.vidaplusbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vidaplus.vidaplusbackend.entities.Internacao;
import br.com.vidaplus.vidaplusbackend.enums.StatusInternacao;

public interface InternacaoRepository extends JpaRepository<Internacao, Long> {

    long countByStatus(StatusInternacao status);

    List<Internacao> findByStatus(StatusInternacao status);
}
