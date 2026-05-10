package com.plataforma.cursos.DTO;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Date;

import com.plataforma.cursos.domain.entities.Cursos;
import com.plataforma.cursos.DTO.SubcategoriaDTO;

public class CursosDTO {

    public Long id;
    public String slug;
    public String nome;
    public String subtitulo;
    public String descricao;
    public String instrutor;
    public String duracao;
    public int numeroAulas;
    public String imagemUrl;
    public Date ultimaAtualizacao;
    public String idioma;
    public String nivel;
    public int alunosMatriculados;
    public float preco;
    public List<SubcategoriaDTO> subcategorias;
    public Set<String> requisitos;

    public CursosDTO() {  
    }

    public static CursosDTO fromEntity(Cursos curso) {
        CursosDTO dto = new CursosDTO();

        dto.id = curso.getId();
        dto.slug = curso.getSlug();
        dto.nome = curso.getNome();
        dto.subtitulo = curso.getSubtitulo();
        dto.descricao = curso.getDescricao();
        dto.instrutor = curso.getInstrutor();
        dto.duracao = curso.getDuracao();
        dto.numeroAulas = curso.getNumeroAulas();
        dto.imagemUrl = curso.getImagemUrl();
        dto.ultimaAtualizacao = curso.getUltimaAtualizacao();
        dto.idioma = curso.getIdioma();
        dto.nivel = curso.getNivel();
        dto.alunosMatriculados = curso.getAlunosMatriculados();
        dto.preco = curso.getPreco();
        dto.requisitos = curso.getRequisitos();

        dto.subcategorias = curso.getSubcategorias().stream()
            .map(sub -> new SubcategoriaDTO(sub.getId(), sub.getNome(), sub.getSlug()))
            .toList();

            return dto;
    }

    public Long getId() { return id; }
    public String getSlug() {return slug; }
    public String getNome() { return nome; }
    public String getSubtitulo() { return subtitulo; }
    public String getDescricao() { return descricao; }
    public String getInstrutor() { return instrutor; }
    public String getDuracao() { return duracao; }
    public int getNumeroAulas() { return numeroAulas; }
    public String getImagemUrl() { return imagemUrl; }
    public Date getUltimaAtualizacao() { return ultimaAtualizacao; }
    public String getIdioma() { return idioma; }
    public String getNivel() { return nivel; }
    public int getAlunosMatriculados() { return alunosMatriculados; }
    public float getPreco() { return preco; }
    public List<SubcategoriaDTO> getSubcategorias() { return subcategorias; }
    public Set<String> getRequisitos() { return requisitos; }
}