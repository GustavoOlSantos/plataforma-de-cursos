package com.plataforma.cursos.DTO;

import java.util.List;

public class CriarModuloDTO {
    public String titulo;
    public String descricao;
    public Integer ordem;
    public List<CriarAulaDTO> aulas;

    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public Integer getOrdem() { return ordem; }
    public List<CriarAulaDTO> getAulas() { return aulas; }
}