package org.serratec.backend.gestao_competencias.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="tb_projetosColaboradores")
public class ProjetoColaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeProjeto;
    private String nomeColaborador;
    private Long idColaborador;


    @CreationTimestamp
    private LocalDateTime dataCriacao;
}
