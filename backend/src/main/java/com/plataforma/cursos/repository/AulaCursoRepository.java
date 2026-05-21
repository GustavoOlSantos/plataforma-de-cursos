package com.plataforma.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.plataforma.cursos.domain.entities.AulaCurso;

public interface AulaCursoRepository extends JpaRepository<AulaCurso, Long> {
}