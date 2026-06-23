package com.plataforma.cursos.domain.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Document (collection  = "avaliacoes_curso")
public class AvaliacoesCurso {

    @Id
    private String id;
    private Integer cursoId;
    private Integer userId;
    private int nota;
    private String mensagem;
    private Date dataAvaliacao;

    public void setDate(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }
}