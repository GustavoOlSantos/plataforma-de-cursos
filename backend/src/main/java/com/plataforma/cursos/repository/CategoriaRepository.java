package com.plataforma.cursos.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.plataforma.cursos.domain.Categoria;
import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query("""
        SELECT DISTINCT c FROM Categoria c
        LEFT JOIN FETCH c.subcategorias sc
        WHERE c.nome IN :nomes
    """)
    List<Categoria> findByNomeIn(@Param("nomes") List<String> nomes);
}