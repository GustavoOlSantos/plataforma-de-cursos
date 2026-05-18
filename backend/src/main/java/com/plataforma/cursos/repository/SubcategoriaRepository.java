package com.plataforma.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.plataforma.cursos.domain.entities.Subcategoria;

public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Long> {
}