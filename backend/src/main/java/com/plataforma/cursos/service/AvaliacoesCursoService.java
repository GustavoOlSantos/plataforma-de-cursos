package com.plataforma.cursos.service;

import org.springframework.stereotype.Service;
import org.springframework.http.*;
import java.util.List;

import com.plataforma.cursos.exception.BusinessException;
import com.plataforma.cursos.domain.documents.AvaliacoesCurso;
import com.plataforma.cursos.repository.AvaliacoesCursoRepository;

@Service
public class AvaliacoesCursoService {

    private final AvaliacoesCursoRepository repository;

    public AvaliacoesCursoService(AvaliacoesCursoRepository repository) {
        this.repository = repository;
    }

    public AvaliacoesCurso register(AvaliacoesCurso avaliacao) {
        if(avaliacao == null){
            throw new BusinessException("Preencha a sua avaliação", true,HttpStatus.BAD_REQUEST);
        }

        return repository.save(avaliacao);
    }

    public List<AvaliacoesCurso> findByCursoId(Integer id){
        return repository.findByCursoId(id);
    }
}