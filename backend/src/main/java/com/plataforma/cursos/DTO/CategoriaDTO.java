package com.plataforma.cursos.DTO;

import java.util.List;

public class CategoriaDTO {

    private String nome;
    private String slug;
    private List<SubcategoriaDTO> subcategorias;

    public CategoriaDTO(String nome, String slug, List<SubcategoriaDTO> subcategorias) {
        this.nome = nome;
        this.slug = slug;
        this.subcategorias = subcategorias;
    }

    public String getNome() { return nome; }
    public String getSlug() { return slug; }
    public List<SubcategoriaDTO> getSubcategorias() { return subcategorias; }
}