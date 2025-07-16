package org.serratec.backend.gestao_competencias.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ProjetoTopicoRequestDTO {

    private Long hardSkillId;
    private List<Long> topicoIds;
}
