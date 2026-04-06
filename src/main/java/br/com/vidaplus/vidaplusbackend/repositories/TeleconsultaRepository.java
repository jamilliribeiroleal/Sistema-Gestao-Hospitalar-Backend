package br.com.vidaplus.vidaplusbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.vidaplus.vidaplusbackend.entities.Teleconsulta;

public interface TeleconsultaRepository extends JpaRepository<Teleconsulta, Long> {
}
