package com.plataforma.cursos.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.plataforma.cursos.domain.Cursos;
import java.util.List;

public interface CursosRepository extends JpaRepository<Cursos, Long> {
    List<Cursos> findByNomeContainingIgnoreCase(String nome);
}