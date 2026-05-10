package com.plataforma.cursos.domain.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document (collection  = "avaliacoes_curso")
public class AvaliacoesCurso {

    @Id
    private String id;
    private Integer cursoId;
    private Integer userId;
    private int nota;
    private String mensagem;

    public String getId() {
        return id;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public int getNota() {
        return nota;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public void setUserId(Integer userId) {
            this.userId = userId;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}