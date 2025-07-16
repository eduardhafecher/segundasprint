package org.serratec.backend.gestao_competencias.DTO;

import java.util.List;

import org.serratec.backend.gestao_competencias.enums.NivelCompetencia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HardSkillResponseDTO {

        private Long id;
        private String nome;
        private List<TopicoResponseDTO> topicos;
        
       
}




