package com.plataforma.cursos.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.plataforma.cursos.domain.Categoria;
import com.plataforma.cursos.DTO.CategoriaDTO;
import com.plataforma.cursos.DTO.SubcategoriaDTO;
import com.plataforma.cursos.exception.BusinessException;
import com.plataforma.cursos.repository.CategoriaRepository;
import java.util.Optional;
import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<CategoriaDTO> findAll() {
        List<Categoria> categorias = repository.findAll();
        return categorias.stream().map(cat -> {
            List<SubcategoriaDTO> subcategorias = cat.getSubcategorias().stream()
                .map(sub -> new SubcategoriaDTO(
                    sub.getId(),
                    sub.getNome(),
                    sub.getSlug()
                ))
                .distinct()
                .toList();

                return new CategoriaDTO(
                    cat.getNome(),
                    cat.getSlug(),
                    subcategorias
                );

            }).toList();
    }

    public List<CategoriaDTO> buscarCategoriasFiltradasFooter(List<String> nomes){
        List<Categoria> categorias = repository.findByNomeIn(nomes);
        
        return categorias.stream().map(cat -> {

        List<SubcategoriaDTO> subcategorias = cat.getSubcategorias().stream()
        .map(sub -> new SubcategoriaDTO(
            sub.getId(),
            sub.getNome(),
            sub.getSlug()
        ))
        .distinct()
        .limit(4)
        .toList();

        return new CategoriaDTO(
            cat.getNome(),
            cat.getSlug(),
            subcategorias
        );

    }).toList();
    }
}