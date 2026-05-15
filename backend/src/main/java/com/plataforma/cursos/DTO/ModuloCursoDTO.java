package com.plataforma.cursos.DTO;

import java.util.List;

import com.plataforma.cursos.domain.entities.ModuloCurso;

public class ModuloCursoDTO {

    public Long id;
    public String titulo;
    public String descricao;
    public Integer ordem;

    public List<AulaCursoDTO> aulas;

    public ModuloCursoDTO(){}

    public static ModuloCursoDTO fromEntity(ModuloCurso modulo){

        ModuloCursoDTO dto = new ModuloCursoDTO();

        dto.id = modulo.getId();
        dto.titulo = modulo.getTitulo();
        dto.descricao = modulo.getDescricao();
        dto.ordem = modulo.getOrdem();

        dto.aulas = modulo.getAulas()
            .stream()
            .map(AulaCursoDTO::fromEntity)
            .toList();

        return dto;
    }
}