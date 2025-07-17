package org.serratec.backend.gestao_competencias.DTO;

import java.time.LocalDate;

import org.serratec.backend.gestao_competencias.enums.NivelPrioridade;
import org.serratec.backend.gestao_competencias.enums.StatusMeta;

import lombok.Data;

@Data
public class MetasEstrategicasRequestDTO {
   
	private String descricao;
    private LocalDate prazo;
    private StatusMeta statusMeta;
    private NivelPrioridade nivelPrioridade;
    private Integer nivelDeDominio;
    private Long hardSkillId;

}

