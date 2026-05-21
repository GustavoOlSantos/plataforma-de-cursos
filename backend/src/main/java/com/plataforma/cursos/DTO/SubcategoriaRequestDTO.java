package com.plataforma.cursos.DTO;

public class SubcategoriaRequestDTO {
    public Long id;
    public String nome;
    public String slug;
    public Long categoriaId;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public String getSlug() {
        return slug;
    }
}
