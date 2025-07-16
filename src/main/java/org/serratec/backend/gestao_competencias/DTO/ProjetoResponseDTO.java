package org.serratec.backend.gestao_competencias.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProjetoResponseDTO {

    private Long Id;
    private String nome;
    private String descricao;

//    private List<CompetenciasResponseDTO> competencias;
    private List<ProjetoTopicoResponseDTO> requisitos;

    public ProjetoResponseDTO() {
    }

}
