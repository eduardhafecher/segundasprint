package org.serratec.backend.gestao_competencias.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ProjetoTopicoResponseDTO {

    private Long hardSkillId;
    private String hardSkillNome;
    private List<String> topicos;

    public Long getHardSkillId() {
        return hardSkillId;
    }


}
