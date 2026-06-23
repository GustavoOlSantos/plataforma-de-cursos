package com.plataforma.cursos.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;

import com.plataforma.cursos.domain.entities.Cursos;
import com.plataforma.cursos.domain.entities.Categoria;

@Getter
@Setter
@SuppressWarnings("unused")
@Entity
@Table(name = "subcategorias")
public class Subcategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String slug;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonIgnore
    private Categoria categoria;

    @ManyToMany
    @JoinTable(
        name = "curso_subcategoria",
        joinColumns = @JoinColumn(name = "subcategoria_id"),
        inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private Set<Cursos> cursos;
}