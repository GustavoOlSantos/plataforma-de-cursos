package com.plataforma.cursos.DTO;

import com.plataforma.cursos.domain.entities.AulaCurso;

public class AulaCursoDTO {

    public Long id;
    public String titulo;
    public String descricao;
    public String videoUrl;
    public String thumbnail;
    public Integer duracaoSegundos;
    public Integer ordem;
    public Boolean gratuita;
    public Boolean publicada;

    public AulaCursoDTO() {}

    public static AulaCursoDTO fromEntity(AulaCurso aula){
        AulaCursoDTO dto = new AulaCursoDTO();

        dto.id = aula.getId();
        dto.titulo = aula.getTitulo();
        dto.descricao = aula.getDescricao();
        dto.videoUrl = aula.getVideo_url();
        dto.thumbnail = aula.getThumbnail();
        dto.duracaoSegundos = aula.getDuracao_segundos();
        dto.ordem = aula.getOrdem();
        dto.gratuita = aula.getGratuita();
        dto.publicada = aula.getPublicada();

        return dto;
    }
}