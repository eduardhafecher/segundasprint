package org.serratec.backend.gestao_competencias.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import org.serratec.backend.gestao_competencias.enums.NivelCompetencia;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="hard_skill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HardSkill {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private NivelCompetencia nivelCompetencia;

//    @ManyToOne
//    @JoinColumn(name= "colaborador_id")
//    private Colaborador  colaborador;
 
//    @OneToMany(mappedBy = "hardSkill")
//    @JsonManagedReference
//    private List<Topico> topicos;

    @OneToMany(mappedBy = "hardSkill", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Topico> topicos = new ArrayList<>();
}
