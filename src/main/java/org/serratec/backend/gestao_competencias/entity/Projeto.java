package org.serratec.backend.gestao_competencias.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "projetos")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String nome;
    private String descricao;

    private Double periodo;

    @ManyToMany
    @JoinTable(name="colaborador_projeto", joinColumns =
    @JoinColumn(name="projeto_id"),
            inverseJoinColumns = @JoinColumn(name = "colaborador_id"))

    //o joinTable define a tabela intermediaria que representa o relacionamento muitos para muitos
    // o joinColumn	define a coluna que representa a chave estrangeira do lado "dono" do relacionamento
    // e a inverse representa a chave estrangeira do outro lado

    private List<Colaborador> colaboradores;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private List<ProjetoTopico> requisitos;
}
