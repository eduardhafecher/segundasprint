package org.serratec.backend.gestao_competencias.entity;

import java.util.List;

import org.serratec.backend.gestao_competencias.enums.NivelCompetencia;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    @ManyToOne
    @JoinColumn(name= "colaborador_id")
    private Colaborador  colaborador;
 
    @OneToMany(mappedBy = "hardSkill")
    @JsonManagedReference
    private List<Topico> topicos;

}
