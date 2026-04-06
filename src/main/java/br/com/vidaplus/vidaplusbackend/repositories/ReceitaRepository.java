package br.com.vidaplus.vidaplusbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vidaplus.vidaplusbackend.entities.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
	@EntityGraph(attributePaths = {"paciente", "profissional", "consulta"})
	List<Receita> findAll();

}
