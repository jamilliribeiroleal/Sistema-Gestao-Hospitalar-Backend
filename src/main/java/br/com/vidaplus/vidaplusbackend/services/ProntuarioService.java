package br.com.vidaplus.vidaplusbackend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vidaplus.vidaplusbackend.dto.ProntuarioCreateDTO;
import br.com.vidaplus.vidaplusbackend.dto.ProntuarioRequestDTO;
import br.com.vidaplus.vidaplusbackend.dto.ProntuarioResponseDTO;
import br.com.vidaplus.vidaplusbackend.entities.Consulta;
import br.com.vidaplus.vidaplusbackend.entities.Paciente;
import br.com.vidaplus.vidaplusbackend.entities.Profissional;
import br.com.vidaplus.vidaplusbackend.entities.Prontuario;
import br.com.vidaplus.vidaplusbackend.exceptions.ResourceNotFoundException;
import br.com.vidaplus.vidaplusbackend.mappers.ProntuarioMapper;
import br.com.vidaplus.vidaplusbackend.repositories.ConsultaRepository;
import br.com.vidaplus.vidaplusbackend.repositories.PacienteRepository;
import br.com.vidaplus.vidaplusbackend.repositories.ProfissionalRepository;
import br.com.vidaplus.vidaplusbackend.repositories.ProntuarioRepository;

@Service
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final PacienteRepository pacienteRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ConsultaRepository consultaRepository;
    private final EncryptionService encryptionService;
    private final ProntuarioMapper prontuarioMapper;

    public ProntuarioService(
            ProntuarioRepository prontuarioRepository,
            PacienteRepository pacienteRepository,
            ProfissionalRepository profissionalRepository,
            ConsultaRepository consultaRepository,
            EncryptionService encryptionService,
            ProntuarioMapper prontuarioMapper
    ) {
        this.prontuarioRepository = prontuarioRepository;
        this.pacienteRepository = pacienteRepository;
        this.profissionalRepository = profissionalRepository;
        this.consultaRepository = consultaRepository;
        this.encryptionService = encryptionService;
        this.prontuarioMapper = prontuarioMapper;
    }

    //  Buscar todos
    @Transactional(readOnly = true)
    public List<ProntuarioResponseDTO> findAll() {
        return prontuarioRepository.findAllWithRelations()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProntuarioResponseDTO findByIdDTO(Long id) {
        Prontuario p = prontuarioRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prontuário não encontrado com id: " + id));
        return toResponseDTO(p);
    }

    // Histórico do paciente
    @Transactional(readOnly = true)
    public List<ProntuarioResponseDTO> buscarHistoricoPaciente(Long pacienteId) {
        return prontuarioRepository
                .findByPacienteIdAndDeletedAtIsNullOrderByCreatedAtDesc(pacienteId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Criar prontuário
    @Transactional
    public ProntuarioResponseDTO save(ProntuarioCreateDTO dto) {
        Prontuario p = new Prontuario();

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));
        p.setPaciente(paciente);

        if (dto.getProfissionalId() != null) {
            Profissional prof = profissionalRepository.findById(dto.getProfissionalId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado"));
            p.setProfissional(prof);
        }

        if (dto.getConsultaId() != null) {
            Consulta c = consultaRepository.findById(dto.getConsultaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));
            p.setConsulta(c);
        }

        //  AES MySQL 
        p.setDescricaoEnc(encryptionService.encryptMySQL(dto.getDescricao()));
        p.setDiagnosticoEnc(
                dto.getDiagnostico() != null
                        ? encryptionService.encryptMySQL(dto.getDiagnostico())
                        : null
        );
        p.setObservacoesEnc(
                dto.getObservacoes() != null
                        ? encryptionService.encryptMySQL(dto.getObservacoes())
                        : null
        );

        return toResponseDTO(prontuarioRepository.save(p));
    }

    // Atualizar prontuário
    @Transactional
    public ProntuarioResponseDTO update(Long id, ProntuarioRequestDTO dto) {
        Prontuario p = prontuarioRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prontuário não encontrado"));

        if (dto.getDescricao() != null) {
            p.setDescricaoEnc(encryptionService.encryptMySQL(dto.getDescricao()));
        }
        if (dto.getDiagnostico() != null) {
            p.setDiagnosticoEnc(encryptionService.encryptMySQL(dto.getDiagnostico()));
        }
        if (dto.getObservacoes() != null) {
            p.setObservacoesEnc(encryptionService.encryptMySQL(dto.getObservacoes()));
        }

        return toResponseDTO(prontuarioRepository.save(p));
    }

    // Soft delete
    @Transactional
    public void delete(Long id) {
        Prontuario p = prontuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prontuário não encontrado"));
        p.setDeletedAt(java.time.LocalDateTime.now());
        prontuarioRepository.save(p);
    }

 //  DESCRIPTOGRAFIA CENTRALIZADA
 private ProntuarioResponseDTO toResponseDTO(Prontuario p) {
        String descricao = encryptionService.decryptMySQL(p.getDescricaoEnc());
        String diagnostico = encryptionService.decryptMySQL(p.getDiagnosticoEnc());
        String observacoes = encryptionService.decryptMySQL(p.getObservacoesEnc());

        return prontuarioMapper.toDTO(
                p,
                descricao,
                diagnostico,
                observacoes
        );
    }
}
