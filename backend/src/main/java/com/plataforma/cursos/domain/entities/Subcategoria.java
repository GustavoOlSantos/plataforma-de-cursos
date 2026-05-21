package com.plataforma.cursos.domain.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;

import com.plataforma.cursos.domain.entities.Cursos;
import com.plataforma.cursos.domain.entities.Categoria;

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

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getSlug() {
        return slug;
    }


    public Set<Cursos> getCursos() {
        return cursos;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setCursos(Set<Cursos> cursos) {
        this.cursos = cursos;
    }

    
}