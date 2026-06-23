package com.plataforma.cursos.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import com.plataforma.cursos.domain.entities.AulaCurso;
import com.plataforma.cursos.domain.entities.Cursos;

@Getter
@Setter
@SuppressWarnings("unused")
@Entity
@Table(name = "modulo_curso")
public class ModuloCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Cursos curso;

    private String titulo;

    private String descricao;

    private Integer ordem;

    @OneToMany(mappedBy = "modulo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AulaCurso> aulas = new ArrayList<>();

    public ModuloCurso() {
    }
}