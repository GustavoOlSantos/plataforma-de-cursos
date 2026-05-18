package com.plataforma.cursos.DTO;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class CriarCursoDTO {
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
    public Set<String> requisitos;
    public List<SubcategoriaRefDTO> subcategorias;
    public List<CriarModuloDTO> modulos;

    public String getSlug() { return slug; }
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
    public Set<String> getRequisitos() { return requisitos; }
    public List<SubcategoriaRefDTO> getSubcategorias() { return subcategorias; }
    public List<CriarModuloDTO> getModulos() { return modulos; }
}