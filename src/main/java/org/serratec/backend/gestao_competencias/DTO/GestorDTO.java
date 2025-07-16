package org.serratec.backend.gestao_competencias.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.serratec.backend.gestao_competencias.entity.Gestor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GestorDTO {

    @NotBlank(message = "O campo 'NOME' não está preenchido")
    private String nome;

    @NotNull
    private String cpf;

    @NotBlank(message = "O campo deve estar preenchido")
    private String ocupacao;

    @NotBlank(message = "O campo deve estar preenchido")
    private String email;

    public GestorDTO(Gestor gestor) {
    }
}
