package br.com.vidaplus.vidaplusbackend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.vidaplus.vidaplusbackend.entities.Prontuario;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {

	// LISTAGEM GERAL COM RELAÇÕES
	@Query("""
			    SELECT DISTINCT p FROM Prontuario p
			    LEFT JOIN FETCH p.paciente
			    LEFT JOIN FETCH p.profissional
			    LEFT JOIN FETCH p.consulta
			    WHERE p.deletedAt IS NULL
			""")
	List<Prontuario> findAllWithRelations();

//  BUSCA POR ID COM RELAÇÕES
	@Query("""
			    SELECT DISTINCT p FROM Prontuario p
			    LEFT JOIN FETCH p.paciente
			    LEFT JOIN FETCH p.profissional
			    LEFT JOIN FETCH p.consulta
			    WHERE p.id = :id
			      AND p.deletedAt IS NULL
			""")
	Optional<Prontuario> findByIdWithRelations(@Param("id") Long id);

//  HISTÓRICO DO PACIENTE
	// DESCRIPTOGRAFAR NO BACKEND
	@Query("""
			    SELECT p FROM Prontuario p
			    LEFT JOIN FETCH p.profissional
			    LEFT JOIN FETCH p.consulta
			    WHERE p.paciente.id = :pacienteId
			      AND p.deletedAt IS NULL
			    ORDER BY p.createdAt DESC
			""")
	List<Prontuario> findByPacienteIdAndDeletedAtIsNullOrderByCreatedAtDesc(@Param("pacienteId") Long pacienteId);

//  HISTÓRICO POR PROFISSIONAL
	@Query("""
			    SELECT p FROM Prontuario p
			    INNER JOIN FETCH p.profissional prof
			    LEFT JOIN FETCH p.consulta
			    WHERE p.paciente.id = :pacienteId
			      AND prof.id = :profissionalId
			      AND p.deletedAt IS NULL
			    ORDER BY p.createdAt DESC
			""")
	List<Prontuario> findByPacienteIdAndProfissionalIdAndDeletedAtIsNullOrderByCreatedAtDesc(
			@Param("pacienteId") Long pacienteId, @Param("profissionalId") Long profissionalId);

}
