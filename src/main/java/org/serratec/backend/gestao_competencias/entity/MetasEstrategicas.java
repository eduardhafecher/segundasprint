package org.serratec.backend.gestao_competencias.entity;

import java.time.LocalDate;

import org.serratec.backend.gestao_competencias.enums.NivelPrioridade;
import org.serratec.backend.gestao_competencias.enums.StatusMeta;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "metasestrategicas")
public class MetasEstrategicas {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String descricao;
	
	private LocalDate prazo;
	
	private StatusMeta statusMeta;
	
	private NivelPrioridade nivelPrioridade;
	
	private Integer NivelDeDominio;
	
    @ManyToOne
	@JoinColumn(name = "hard_skill_id")
	private HardSkill hardSkill;
}
