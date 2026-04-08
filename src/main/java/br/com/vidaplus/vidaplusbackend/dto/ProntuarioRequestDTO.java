package br.com.vidaplus.vidaplusbackend.dto;

import br.com.vidaplus.vidaplusbackend.entities.Prontuario;
import br.com.vidaplus.vidaplusbackend.services.EncryptionService;

public class ProntuarioRequestDTO {

    private String descricao;
    private String diagnostico;
    private String observacoes;

    public ProntuarioRequestDTO() {}

    public void applyToEntity(Prontuario prontuario, EncryptionService encryptionService) {

        if (descricao != null && !descricao.isBlank()) {
            prontuario.setDescricaoEnc(
                encryptionService.encryptMySQL(descricao.trim())
            );
        }

        if (diagnostico != null && !diagnostico.isBlank()) {
            prontuario.setDiagnosticoEnc(
                encryptionService.encryptMySQL(diagnostico.trim())
            );
        }

        if (observacoes != null && !observacoes.isBlank()) {
            prontuario.setObservacoesEnc(
                encryptionService.encryptMySQL(observacoes.trim())
            );
        }
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
