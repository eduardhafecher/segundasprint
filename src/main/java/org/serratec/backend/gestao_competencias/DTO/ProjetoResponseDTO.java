package org.serratec.backend.gestao_competencias.DTO;

import lombok.Getter;
import lombok.Setter;
import org.serratec.backend.gestao_competencias.enums.StatusProjeto;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class ProjetoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataConclusao;
    private StatusProjeto status;

//    private List<CompetenciasResponseDTO> competencias;
    private List<ProjetoTopicoResponseDTO> requisitos;

    public ProjetoResponseDTO() {
    }

}
