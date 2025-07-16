package org.serratec.backend.gestao_competencias.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class ColaboradorHardSkillResponseDTO {

    private Long hardSkillId;
    private String nomeHardSkill;
    private Integer totalTopicos;
    private Integer topicosDominados;
    private List<String> topicosDominadosNomes;
    private Double percentualDominio;

}
