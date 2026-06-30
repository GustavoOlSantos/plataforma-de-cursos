package com.plataforma.cursos.service;

import org.springframework.stereotype.Service;
import org.springframework.http.*;
import java.util.List;
import java.util.Date;

import com.plataforma.cursos.exception.BusinessException;
import com.plataforma.cursos.domain.documents.AvaliacoesCurso;
import com.plataforma.cursos.repository.AvaliacoesCursoRepository;

@Service
public class AvaliacoesCursoService {

    private final AvaliacoesCursoRepository repository;

    public AvaliacoesCursoService(AvaliacoesCursoRepository repository) {
        this.repository = repository;
    }

    public AvaliacoesCurso register(AvaliacoesCurso avaliacao, int userId) {
        if(avaliacao == null || avaliacao.getCursoId() == null || avaliacao.getMensagem() == null || avaliacao.getMensagem().trim().isEmpty()) {
            throw new BusinessException("Preencha a sua avaliação", true,HttpStatus.UNPROCESSABLE_CONTENT, "create-avaliacao");
        }

        if(avaliacao.getNota() > 5 || avaliacao.getNota() <= 0){
            throw new BusinessException("A nota deve ser entre 1 e 5", true,HttpStatus.UNPROCESSABLE_CONTENT, "create-avaliacao");
        }

        avaliacao.setUserId(userId);
        avaliacao.setDate(new Date());

        return repository.save(avaliacao);
    }

    public List<AvaliacoesCurso> findByCursoId(Integer id){
        return repository.findByCursoId(id);
    }
}