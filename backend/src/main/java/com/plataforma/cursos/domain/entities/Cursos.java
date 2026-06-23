package com.plataforma.cursos.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import com.plataforma.cursos.domain.entities.ModuloCurso;
import com.plataforma.cursos.domain.entities.Subcategoria;

@Getter
@Setter
@SuppressWarnings("unused")
@Entity
@Table(name = "cursos")
public class Cursos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String slug;
    @Column(columnDefinition = "VARCHAR(255)")
    private String nome;
    @Column(columnDefinition = "VARCHAR(500)")
    private String subtitulo;
    @Column(columnDefinition = "TEXT")
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

    @ManyToMany(mappedBy = "cursos")
    private List<Subcategoria> subcategorias = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
        name = "curso_requisito",
        joinColumns = @JoinColumn(name = "curso_id")
    )
    @Column(name = "descricao")
    private Set<String> requisitos;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModuloCurso> modulos;

    public boolean isValido(){
        return nome != null && !nome.isEmpty() && 
               slug != null && !slug.isEmpty() && 
               descricao != null && !descricao.isEmpty() && 
               duracao != null && !duracao.isEmpty() &&
               instrutor != null && !instrutor.isEmpty() &&
               idioma != null && !idioma.isEmpty() &&
               nivel != null && !nivel.isEmpty() &&
               preco > 0 &&
               numeroAulas > 0;
    }
}