package com.plataforma.cursos.domain.entities;

import jakarta.persistence.*;
import com.plataforma.cursos.domain.entities.ModuloCurso;

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

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getDuracao_segundos() {
        return duracao_segundos;
    }

    public void setDuracao_segundos(Integer duracao_segundos) {
        this.duracao_segundos = duracao_segundos;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public Boolean getGratuita() {
        return gratuita;
    }

    public void setGratuita(Boolean gratuita) {
        this.gratuita = gratuita;
    }

    public Boolean getPublicada() {
        return publicada;
    }

    public void setPublicada(Boolean publicada) {
        this.publicada = publicada;
    }

    private ModuloCurso getModulo() {
        return modulo;
    }

    public Long getModuloId() {
        return modulo != null ? modulo.getId() : null;
    }

    public void setModulo(ModuloCurso modulo) {
        this.modulo = modulo;
    }
}