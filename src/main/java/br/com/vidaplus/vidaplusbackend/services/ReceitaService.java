package br.com.vidaplus.vidaplusbackend.services;

import br.com.vidaplus.vidaplusbackend.dto.ReceitaRequestDTO;
import br.com.vidaplus.vidaplusbackend.entities.Consulta;
import br.com.vidaplus.vidaplusbackend.entities.Paciente;
import br.com.vidaplus.vidaplusbackend.entities.Profissional;
import br.com.vidaplus.vidaplusbackend.entities.Receita;
import br.com.vidaplus.vidaplusbackend.exceptions.ResourceNotFoundException;
import br.com.vidaplus.vidaplusbackend.repositories.ReceitaRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReceitaService {

	private final ReceitaRepository receitaRepository;
	private final EntityManager entityManager;

	public ReceitaService(ReceitaRepository receitaRepository, EntityManager entityManager) {
		this.receitaRepository = receitaRepository;
		this.entityManager = entityManager;
	}

// BUSCAS

	@Transactional(Transactional.TxType.SUPPORTS)
	public List<Receita> findAll() {
		return receitaRepository.findAll();
	}

	@Transactional(Transactional.TxType.SUPPORTS)
	public Receita findById(Long id) {
		return receitaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada com id: " + id));
	}

	// CRIAÇÃO

	public Receita save(ReceitaRequestDTO dto) {

		Receita receita = new Receita();

		receita.setDataEmissao(dto.getDataEmissao());
		receita.setConteudoEnc(dto.getConteudo().getBytes());
		receita.setReceitaPdfUrl(dto.getReceitaPdfUrl());
		receita.setAssinaturaDigital(dto.getAssinaturaDigital());

		receita.setPaciente(entityManager.getReference(Paciente.class, dto.getPacienteId()));

		if (dto.getProfissionalId() != null) {
			receita.setProfissional(entityManager.getReference(Profissional.class, dto.getProfissionalId()));
		}

		if (dto.getConsultaId() != null) {
			receita.setConsulta(entityManager.getReference(Consulta.class, dto.getConsultaId()));
		}

		return receitaRepository.save(receita);
	}

	// ATUALIZAÇÃO

	public Receita update(Long id, ReceitaRequestDTO dto) {

		Receita receita = findById(id);

		receita.setDataEmissao(dto.getDataEmissao());
		receita.setConteudoEnc(dto.getConteudo().getBytes());
		receita.setReceitaPdfUrl(dto.getReceitaPdfUrl());
		receita.setAssinaturaDigital(dto.getAssinaturaDigital());

		
		if (dto.getProfissionalId() != null) {
			receita.setProfissional(entityManager.getReference(Profissional.class, dto.getProfissionalId()));
		}

		if (dto.getConsultaId() != null) {
			receita.setConsulta(entityManager.getReference(Consulta.class, dto.getConsultaId()));
		}

		return receitaRepository.save(receita);
	}

// REMOÇÃO

	public void delete(Long id) {
		Receita receita = findById(id);
		receitaRepository.delete(receita);
	}
}
