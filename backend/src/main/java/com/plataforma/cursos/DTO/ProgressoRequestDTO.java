package com.plataforma.cursos.DTO;

import java.time.LocalDateTime;

public class ProgressoRequestDTO {

    private Long aulaId;
    private Integer ultimoSegundo;
    private LocalDateTime ultimaVisualizacao;
    private Boolean concluida;

    public Long getAulaId() {
        return aulaId;
    }

    public void setAulaId(Long aulaId) {
        this.aulaId = aulaId;
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
}