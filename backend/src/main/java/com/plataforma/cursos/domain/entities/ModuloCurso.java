package com.plataforma.cursos.domain.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.plataforma.cursos.domain.entities.AulaCurso;
import com.plataforma.cursos.domain.entities.Cursos;

@Entity
@Table(name = "modulo_curso")
public class ModuloCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Cursos curso;

    private String titulo;

    private String descricao;

    private Integer ordem;

    @OneToMany(mappedBy = "modulo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AulaCurso> aulas = new ArrayList<>();

    // CONSTRUTOR

    public ModuloCurso() {
    }

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cursos getCurso() {
        return curso;
    }

    public void setCurso(Cursos curso) {
        this.curso = curso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public List<AulaCurso> getAulas() {
        return aulas;
    }

    public void setAulas(List<AulaCurso> aulas) {
        this.aulas = aulas;
    }
}