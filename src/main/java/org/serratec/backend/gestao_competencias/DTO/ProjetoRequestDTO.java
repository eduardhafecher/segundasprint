package org.serratec.backend.gestao_competencias.DTO;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoRequestDTO {

    private String nome;
    private String descricao;
    private List<Long> colaboradoresIds;
    private List<Long> topicosIds;
    private List<ProjetoTopicoRequestDTO> topicos;



    //public ProjetoRequestDTO(Projeto projeto) {
    // }
}
