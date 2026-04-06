package br.com.vidaplus.vidaplusbackend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vidaplus.vidaplusbackend.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {


    // Pacientes ativos (listas públicas / profissionais)
    List<Paciente> findByAtivoTrue();

    // Buscar paciente ativo por ID
    Optional<Paciente> findByIdAndAtivoTrue(Long id);

    // Contagem de pacientes ativos
    long countByAtivoTrue();

    // Contagem total (ativos + inativos)
    long count();
}
