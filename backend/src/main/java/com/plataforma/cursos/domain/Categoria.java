package com.plataforma.cursos.domain;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;

import com.plataforma.cursos.domain.Cursos;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    @JsonIgnore
    @ManyToMany(mappedBy = "categorias")
    private Set<Cursos> cursos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
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

    public void setCursos(Set<Cursos> cursos) {
        this.cursos = cursos;
    }
}