package com.plataforma.cursos.domain;
import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.plataforma.cursos.domain.Categoria;

@Entity
@Table(name = "cursos")
public class Cursos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String subtitulo;
    private String descricao;
    private String instrutor;
    private String duracao;
    private int numeroAulas;
    private String imagemUrl;
    private Date ultimaAtualizacao;
    private String idioma;
    private String nivel;
    private int alunosMatriculados;
    private float preco;

    //=> Faz o Join com a tabela curso_categoria
    @ManyToMany
    @JoinTable(
        name = "curso_categoria",
        joinColumns = @JoinColumn(name = "curso_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<Categoria> categorias = new HashSet<>();

    @ElementCollection
    @CollectionTable(
        name = "curso_requisito",
        joinColumns = @JoinColumn(name = "curso_id")
    )
    @Column(name = "descricao")
    private Set<String> requisitos;

    public boolean isValido(){
        return nome != null && !nome.isEmpty() && 
               descricao != null && !descricao.isEmpty() && 
               duracao != null && !duracao.isEmpty() &&
               instrutor != null && !instrutor.isEmpty() &&
               idioma != null && !idioma.isEmpty() &&
               nivel != null && !nivel.isEmpty() &&
               preco > 0 &&
               numeroAulas > 0;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(String instrutor) {
        this.instrutor = instrutor;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public int getNumeroAulas() {
        return numeroAulas;
    }

    public void setNumeroAulas(int numeroAulas) {
        this.numeroAulas = numeroAulas;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public Date getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(Date ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public void setAlunosMatriculados(int alunosMatriculados) {
        this.alunosMatriculados = alunosMatriculados;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Set<String> getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(Set<String> requisitos) {
        this.requisitos = requisitos;
    }
}