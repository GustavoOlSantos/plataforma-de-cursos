package com.plataforma.cursos.repository;

import org.springframework.data.mongodb.repository.MongoRepository; 
import org.springframework.stereotype.Repository;
import com.plataforma.cursos.domain.documents.AvaliacoesCurso;
import java.util.List;

@Repository
public interface AvaliacoesCursoRepository 
    extends MongoRepository<AvaliacoesCurso, String> {

    List<AvaliacoesCurso> findByCursoId(Integer cursoId);
    List<AvaliacoesCurso> findByCursoIdIn(List<Integer> cursoIds);
    
}