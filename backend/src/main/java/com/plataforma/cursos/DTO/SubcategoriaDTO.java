package com.plataforma.cursos.DTO;

public class SubcategoriaDTO {

    private Long id;
    private String nome;
    private String slug;

    public SubcategoriaDTO(Long id, String nome, String slug) {
        this.id = id;
        this.nome = nome;
        this.slug = slug;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getSlug() { return slug; }
}