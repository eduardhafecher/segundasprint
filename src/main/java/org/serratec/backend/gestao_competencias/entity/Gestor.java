package org.serratec.backend.gestao_competencias.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
//@Table(name = "tb_gestores")
public class Gestor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Column(unique = true, nullable = false)
    private String cpf;
    private String ocupacao;
    private String email;
    private String password;

    @CreationTimestamp
    private LocalDate dataCriacao;
}

