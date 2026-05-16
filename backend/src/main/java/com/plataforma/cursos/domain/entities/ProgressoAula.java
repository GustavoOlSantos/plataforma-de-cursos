package com.plataforma.cursos.domain.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "progresso_aula",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id", "aula_id"})
    }
)
public class ProgressoAula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ultimoSegundo;
    private LocalDateTime ultimaVisualizacao;
    private Boolean concluida;
    private LocalDateTime dataConclusao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "aula_id")
    private AulaCurso aula;

    // CONSTRUTOR

    public ProgressoAula() {
    }

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUltimoSegundo() {
        return ultimoSegundo;
    }

    public void setUltimoSegundo(Integer ultimoSegundo) {
        this.ultimoSegundo = ultimoSegundo;
    }

    public LocalDateTime getUltimaVisualizacao() {
        return ultimaVisualizacao;
    }

    public void setUltimaVisualizacao(LocalDateTime ultimaVisualizacao) {
        this.ultimaVisualizacao = ultimaVisualizacao;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public AulaCurso getAula() {
        return aula;
    }

    public void setAula(AulaCurso aula) {
        this.aula = aula;
    }
}