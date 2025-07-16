package org.serratec.backend.gestao_competencias.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "colaborador_hard_skill")
public class ColaboradorHardSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "colaborador_id", nullable = false)
    private Colaborador colaborador;

    @ManyToOne
    @JoinColumn(name = "hard_skill_id", nullable = false)
    private HardSkill hardSkill;

    // topicos que o colaborador domina
    @ManyToMany
    @JoinTable(
            name = "colaborador_topico_dominado",
            joinColumns = @JoinColumn(name = "colaborador_hard_skill_id"),
            inverseJoinColumns = @JoinColumn(name = "topico_id"))
    private List<Topico> topicosDominados = new ArrayList<>();


}
