package br.com.vidaplus.vidaplusbackend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vidaplus.vidaplusbackend.entities.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {


    // lista apenas profissionais ativos
    List<Profissional> findByAtivoTrue();

    // busca profissional ativo por ID
    Optional<Profissional> findByIdAndAtivoTrue(Long id);


    long countByAtivoTrue();
}
