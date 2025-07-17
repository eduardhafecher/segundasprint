package org.serratec.backend.gestao_competencias.DTO;

import lombok.*;
import org.serratec.backend.gestao_competencias.enums.StatusProjeto;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoRequestDTO {

    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private StatusProjeto status;
    private List<Long> colaboradoresIds;
    private List<Long> topicosIds;
    private List<ProjetoTopicoRequestDTO> topicos;


    //public ProjetoRequestDTO(Projeto projeto) {
    // }
}
