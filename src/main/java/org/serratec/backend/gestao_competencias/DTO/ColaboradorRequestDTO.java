package org.serratec.backend.gestao_competencias.DTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.serratec.backend.gestao_competencias.entity.Colaborador;
import org.serratec.backend.gestao_competencias.DTO.HardSkillRequestDTO;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) //
public class ColaboradorRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
//    @CPF(message = "O campo CPF deve ser VÁLIDO.")
    private String cpf;

    @NotBlank
    private String ocupacao;

    @Email
    private String email;


    private String password;
    
    private List<HardSkillRequestDTO> hardSkills;



    public ColaboradorRequestDTO(Colaborador colaborador) {
        this.nome = colaborador.getNome();
        this.cpf = colaborador.getCpf();
        this.ocupacao = colaborador.getOcupacao();
        this.cpf = colaborador.getCpf();
        this.email = colaborador.getEmail();
        this.password = colaborador.getPassword();


    }

    
}
