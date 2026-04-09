package br.com.vidaplus.vidaplusbackend.services;

import br.com.vidaplus.vidaplusbackend.dto.UnidadeResponseDTO;
import br.com.vidaplus.vidaplusbackend.entities.Unidade;
import br.com.vidaplus.vidaplusbackend.exceptions.ResourceNotFoundException;
import br.com.vidaplus.vidaplusbackend.repositories.UnidadeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UnidadeService {

	private final UnidadeRepository unidadeRepository;

	public UnidadeService(UnidadeRepository unidadeRepository) {
		this.unidadeRepository = unidadeRepository;
	}

	// MÉTODOS INTERNOS (ENTITY)

	public Optional<Unidade> findOptionalById(Long id) {
		return unidadeRepository.findById(id);
	}

	public boolean existsById(Long id) {
		return unidadeRepository.existsById(id);
	}

	protected Unidade findEntityById(Long id) {
		return unidadeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Unidade não encontrada com id: " + id));
	}

	// MÉTODOS EXPOSITOS (DTO)

	public List<UnidadeResponseDTO> listar() {
		return unidadeRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
	}

	public UnidadeResponseDTO buscarPorId(Long id) {
		Unidade unidade = findEntityById(id);
		return toResponseDTO(unidade);
	}

	public UnidadeResponseDTO salvar(Unidade unidade) {
		Unidade salva = unidadeRepository.save(unidade);
		return toResponseDTO(salva);
	}

	public UnidadeResponseDTO atualizar(Long id, Unidade unidadeAtualizada) {
		Unidade unidade = findEntityById(id);

		unidade.setNome(unidadeAtualizada.getNome());
		unidade.setTipo(unidadeAtualizada.getTipo());
		unidade.setUpdatedAt(LocalDateTime.now());

		Unidade atualizada = unidadeRepository.save(unidade);
		return toResponseDTO(atualizada);
	}

	// DELETE (SOFT DELETE)

	public void deletar(Long id) {
		Unidade unidade = findEntityById(id);
		unidade.setDeletedAt(LocalDateTime.now());
		unidadeRepository.save(unidade);
	}

// MAPEAMENTO ENTITY → DTO

	private UnidadeResponseDTO toResponseDTO(Unidade unidade) {
		return new UnidadeResponseDTO(unidade.getId(), unidade.getNome(), unidade.getTipo(), null, null,
				unidade.getCreatedAt(), unidade.getUpdatedAt());
	}
}
