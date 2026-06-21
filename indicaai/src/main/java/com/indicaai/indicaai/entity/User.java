package com.indicaai.indicaai.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "alunos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(length = 100)
    private String curso;

    private Integer semestre;

    @Column(length = 500)
    private String habilidades;

    @Column(length = 200)
    private String github;

    @Column(length = 200)
    private String linkedin;

    @Column(length = 200)
    private String portfolio;

    @JsonIgnore
    @Column(length = 200)
    private String senha;
}