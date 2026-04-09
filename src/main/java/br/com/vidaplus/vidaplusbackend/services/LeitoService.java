package br.com.vidaplus.vidaplusbackend.services;

import br.com.vidaplus.vidaplusbackend.dto.LeitoResponseDTO;
import br.com.vidaplus.vidaplusbackend.entities.Leito;
import br.com.vidaplus.vidaplusbackend.repositories.LeitoRepository;
import br.com.vidaplus.vidaplusbackend.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeitoService {

    private final LeitoRepository leitoRepository;

    public LeitoService(LeitoRepository leitoRepository) {
        this.leitoRepository = leitoRepository;
    }

    public List<LeitoResponseDTO> listar() {
        return leitoRepository.findAll()
                .stream()
                .map(LeitoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public LeitoResponseDTO buscarPorId(Long id) {
        Leito leito = leitoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Leito não encontrado com id: " + id));
        return LeitoResponseDTO.fromEntity(leito);
    }

    public LeitoResponseDTO salvar(Leito leito) {
        Leito salvo = leitoRepository.save(leito);
        return LeitoResponseDTO.fromEntity(salvo);
    }

    public LeitoResponseDTO atualizar(Long id, Leito leitoAtualizado) {
        Leito leito = leitoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Leito não encontrado com id: " + id));

        leito.setNumero(leitoAtualizado.getNumero());
        leito.setStatus(leitoAtualizado.getStatus());

        return LeitoResponseDTO.fromEntity(leitoRepository.save(leito));
    }

    public void deletar(Long id) {
        Leito leito = leitoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Leito não encontrado com id: " + id));
        leitoRepository.delete(leito);
    }
}
