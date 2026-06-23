package com.plataforma.cursos.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import com.plataforma.cursos.domain.entities.ModuloCurso;

@Getter
@Setter
@SuppressWarnings("unused")
@Entity
@Table(name = "aula_modulo")
public class AulaCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private String video_url;
    private String thumbnail;
    private Integer duracao_segundos;
    private Integer ordem;
    private Boolean gratuita;
    private Boolean publicada;

    @ManyToOne
    @JoinColumn(name = "modulo_id")
    private ModuloCurso modulo;


    private ModuloCurso getModulo() {
        return modulo;
    }

    public Long getModuloId() {
        return modulo != null ? modulo.getId() : null;
    }
}