package br.com.vidaplus.vidaplusbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.vidaplus.vidaplusbackend.entities.Unidade;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
	

}
