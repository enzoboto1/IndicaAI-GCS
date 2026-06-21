package com.indicaai.indicaai.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "vagas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, length = 150)
    private String empresa;

    @Column(nullable = false, length = 1000)
    private String descricao;

    @Column(nullable = false, length = 500)
    private String requisitos;

    @Column(nullable = false, length = 50)
    private String modalidade;

    @Column(nullable = false, length = 50)
    private String nivel;
}