package com.plataforma.cursos.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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

    public ProgressoAula() {
    }
}