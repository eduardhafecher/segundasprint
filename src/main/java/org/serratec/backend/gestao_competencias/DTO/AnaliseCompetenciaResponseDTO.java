package org.serratec.backend.gestao_competencias.DTO;

import lombok.Getter;
import lombok.Setter;
import org.serratec.backend.gestao_competencias.enums.NivelCompetencia;
import org.serratec.backend.gestao_competencias.enums.StatusAlinhamento;

@Setter
@Getter
public class AnaliseCompetenciaResponseDTO {

    private Long id;
    private String hardSkillNome;
    private Integer quantidadeDesejada;
    private Integer quantidadeAtual;
    private StatusAlinhamento statusAlinhamento;
}
