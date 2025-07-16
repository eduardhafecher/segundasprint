package org.serratec.backend.gestao_competencias.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_fotos")
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Lob
    private byte[] dados;
    private String tipo;
    private String nome;

    @OneToOne
    @JoinColumn(name = "id_colaborador")
    private Colaborador colaborador;

    @OneToOne
    @JoinColumn(name = "id_gestor")
    private Gestor gestor;
}
