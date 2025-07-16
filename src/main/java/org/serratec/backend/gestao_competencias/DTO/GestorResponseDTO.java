package org.serratec.backend.gestao_competencias.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GestorResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String ocupacao;

}
