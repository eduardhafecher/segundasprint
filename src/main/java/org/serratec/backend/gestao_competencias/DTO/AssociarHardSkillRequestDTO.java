package org.serratec.backend.gestao_competencias.DTO;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AssociarHardSkillRequestDTO {

    private Long hardSkillId;
    private List<Long> topicosDominadosIds;
}
