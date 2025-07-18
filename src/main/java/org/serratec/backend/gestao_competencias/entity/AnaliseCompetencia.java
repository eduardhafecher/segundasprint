package org.serratec.backend.gestao_competencias.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.serratec.backend.gestao_competencias.enums.NivelCompetencia;
import org.serratec.backend.gestao_competencias.enums.StatusAlinhamento;

@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Table(name = "analise_competencias")
public class AnaliseCompetencia {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "hard_skill_id", nullable = false)
        private HardSkill hardSkill;

        @Column(nullable = false)
        private Integer quantidadeColaboradoresDesejada;

        @Column(nullable = false)
        private Integer quantidadeAtual;

        @Enumerated(EnumType.STRING)
        private StatusAlinhamento statusAlinhamento;
}
