package com.plataforma.cursos.repository;

import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.plataforma.cursos.domain.entities.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    boolean existsByUsuarioIdAndCursoId(Long userId, Long cursoId);
    List<Compra> findByUsuarioId(Long userId);
}