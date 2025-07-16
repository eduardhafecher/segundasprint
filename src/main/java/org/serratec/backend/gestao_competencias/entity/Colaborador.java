package org.serratec.backend.gestao_competencias.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
//@Table(name = "tb_colaboradores")
public class Colaborador {

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

    @OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL)
    private List<ColaboradorHardSkill> hardSkillsAssociadas = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "colaborador_soft_skill",
            joinColumns = @JoinColumn(name = "colaborador_id"),
            inverseJoinColumns = @JoinColumn(name = "soft_skill_id"))
    private List<SoftSkill> softSkills = new ArrayList<>();

    @ManyToMany(mappedBy = "colaboradores")
    private List<Projeto> projetos = new ArrayList<>();

}
