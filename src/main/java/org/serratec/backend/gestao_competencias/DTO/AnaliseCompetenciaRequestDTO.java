package org.serratec.backend.gestao_competencias.DTO;

import lombok.Getter;
import lombok.Setter;
import org.serratec.backend.gestao_competencias.enums.NivelCompetencia;
@Setter
@Getter
public class AnaliseCompetenciaRequestDTO {

    private Long hardSkillId;
    private Integer quantidadeDesejada;
}
