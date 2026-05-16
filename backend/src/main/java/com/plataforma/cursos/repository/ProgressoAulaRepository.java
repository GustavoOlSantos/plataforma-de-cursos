package com.plataforma.cursos.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.plataforma.cursos.domain.entities.ProgressoAula;

public interface ProgressoAulaRepository
extends JpaRepository<ProgressoAula, Long> {

    Optional<ProgressoAula> findByUsuarioIdAndAulaId(Long usuarioId, Long aulaId);
    Optional<ProgressoAula> findTopByUsuarioIdAndAulaModuloCursoIdOrderByUltimaVisualizacaoDesc(Long usuarioId, Integer cursoId);
    @Query("""
    SELECT pa.aula.id
    FROM ProgressoAula pa
    JOIN pa.aula a
    JOIN a.modulo m
    JOIN m.curso c
    WHERE pa.usuario.id = :userId
    AND pa.concluida = true
    AND c.id = :cursoId
    """)
    List<Integer> findAulasConcluidas(@Param("userId") Long userId,
                                    @Param("cursoId") Integer cursoId);
}