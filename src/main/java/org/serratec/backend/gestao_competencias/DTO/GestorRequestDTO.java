package org.serratec.backend.gestao_competencias.DTO;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import org.serratec.backend.gestao_competencias.entity.Gestor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GestorRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    @CPF(message = "O campo CPF deve ser VÁLIDO.")
    private String cpf;

    @NotBlank
    private String ocupacao;

    @Email
    private String email;

    @NotBlank
    private String password;

    private Set<GestorDTO> gestores = new HashSet<>();

    public GestorRequestDTO(Gestor gestor) {
        this.nome = gestor.getNome();
        this.password = gestor.getPassword();
        this.email = gestor.getEmail();
    }
}
