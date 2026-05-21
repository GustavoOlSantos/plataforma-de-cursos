package com.plataforma.cursos.domain.entities;
import jakarta.persistence.*;
import java.util.Set;

import com.plataforma.cursos.domain.entities.Subcategoria;

@SuppressWarnings("unused")
@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String slug;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private Set<Subcategoria> subcategorias;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSlug() {
        return slug;
    }

    public Set<Subcategoria> getSubcategorias() {
        return subcategorias;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setSubcategorias(Set<Subcategoria> subcategorias) {
        this.subcategorias = subcategorias;
    }
}