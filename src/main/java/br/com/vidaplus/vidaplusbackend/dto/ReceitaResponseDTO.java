package br.com.vidaplus.vidaplusbackend.dto;

import br.com.vidaplus.vidaplusbackend.entities.Receita;

import java.time.LocalDateTime;

public class ReceitaResponseDTO {

    private Long id;

    private Long pacienteId;
    private Long profissionalId;
    private Long consultaId;

    private LocalDateTime dataEmissao;

    private String conteudo;

    private String receitaPdfUrl;
    private String assinaturaDigital;

    private LocalDateTime createdAt;

 
    public static ReceitaResponseDTO fromEntity(Receita receita) {

        if (receita == null) return null;

        ReceitaResponseDTO dto = new ReceitaResponseDTO();

        dto.id = receita.getId();

        dto.pacienteId = receita.getPaciente() != null
                ? receita.getPaciente().getId()
                : null;

        dto.profissionalId = receita.getProfissional() != null
                ? receita.getProfissional().getId()
                : null;

        dto.consultaId = receita.getConsulta() != null
                ? receita.getConsulta().getId()
                : null;

        dto.dataEmissao = receita.getDataEmissao();


        dto.conteudo = receita.getConteudoEnc() != null
                ? new String(receita.getConteudoEnc())
                : null;

        dto.receitaPdfUrl = receita.getReceitaPdfUrl();
        dto.assinaturaDigital = receita.getAssinaturaDigital();
        dto.createdAt = receita.getCreatedAt();

        return dto;
    }

    public Long getId() { return id; }
    public Long getPacienteId() { return pacienteId; }
    public Long getProfissionalId() { return profissionalId; }
    public Long getConsultaId() { return consultaId; }
    public LocalDateTime getDataEmissao() { return dataEmissao; }
    public String getConteudo() { return conteudo; }
    public String getReceitaPdfUrl() { return receitaPdfUrl; }
    public String getAssinaturaDigital() { return assinaturaDigital; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
