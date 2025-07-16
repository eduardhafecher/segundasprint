package org.serratec.backend.gestao_competencias.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "projeto_requisitos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoTopico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "hard_skill_id")
    private HardSkill hardSkill;

    @ManyToMany
    @JoinTable(name = "requisito_topicos",
            joinColumns = @JoinColumn(name = "requisito_id"),
            inverseJoinColumns = @JoinColumn(name = "topico_id"))
    private List<Topico> topicos;

}
