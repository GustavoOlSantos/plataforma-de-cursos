package com.plataforma.cursos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plataforma.cursos.domain.entities.AulaCurso;

public interface AulaCursoRepository
extends JpaRepository<AulaCurso, Long> {
}