package org.serratec.backend.gestao_competencias.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ColaboradorResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String ocupacao;

    private List<ColaboradorHardSkillResponseDTO> hardSkills;;
    private List<SoftSkillDTO> softSkills;


}
