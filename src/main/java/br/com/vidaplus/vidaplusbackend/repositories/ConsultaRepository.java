package br.com.vidaplus.vidaplusbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vidaplus.vidaplusbackend.entities.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    // consultas de um paciente
    List<Consulta> findByPacienteId(Long pacienteId);

    // consultas da agenda do profissional
    List<Consulta> findByProfissionalId(Long profissionalId);
}
