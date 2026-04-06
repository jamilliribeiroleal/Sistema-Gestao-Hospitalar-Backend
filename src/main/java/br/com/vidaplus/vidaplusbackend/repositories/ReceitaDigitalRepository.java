package br.com.vidaplus.vidaplusbackend.repositories;

import br.com.vidaplus.vidaplusbackend.entities.ReceitaDigital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaDigitalRepository extends JpaRepository<ReceitaDigital, Long> {

    //  Buscar receitas por paciente
    List<ReceitaDigital> findByPacienteId(Long pacienteId);

    //  Buscar receitas por profissional
    List<ReceitaDigital> findByProfissionalId(Long profissionalId);

    //  Buscar receitas por consulta (opcional)
    List<ReceitaDigital> findByConsultaId(Long consultaId);
}
