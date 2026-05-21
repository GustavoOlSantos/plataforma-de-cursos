package com.plataforma.cursos.DTO;

import com.plataforma.cursos.domain.entities.Subcategoria;

import jakarta.persistence.OneToMany;

public class CategoriaRequestDTO {
    private Long id;
    private String nome;
    private String slug;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSlug() {
        return slug;
    }
}
