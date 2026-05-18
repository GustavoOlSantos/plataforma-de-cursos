package com.plataforma.cursos.DTO;

public class CriarAulaDTO {
    public String titulo;
    public String descricao;
    public String videoUrl;
    public String thumbnail;
    public Integer duracaoSegundos;
    public Integer ordem;
    public Boolean gratuita;
    public Boolean publicada;

    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getVideoUrl() { return videoUrl; }
    public String getThumbnail() { return thumbnail; }
    public Integer getDuracaoSegundos() { return duracaoSegundos; }
    public Integer getOrdem() { return ordem; }
    public Boolean getGratuita() { return gratuita; }
    public Boolean getPublicada() { return publicada; }
}