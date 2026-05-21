package com.plataforma.cursos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.plataforma.cursos.DTO.SubcategoriaRequestDTO;
import com.plataforma.cursos.domain.entities.Categoria;
import com.plataforma.cursos.domain.entities.Subcategoria;
import com.plataforma.cursos.exception.BusinessException;
import com.plataforma.cursos.repository.CategoriaRepository;
import com.plataforma.cursos.repository.SubcategoriaRepository;
import org.springframework.http.HttpStatus;

@Service
public class SubcategoriaService {

    @Autowired
    private SubcategoriaRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Subcategoria save(SubcategoriaRequestDTO dto) {

        if(dto.getNome() == null || dto.getSlug() == null || dto.getCategoriaId() == null || dto.getNome().isEmpty() || dto.getSlug().isEmpty()
        || dto.getCategoriaId() == 0) {
            throw new BusinessException("Dados incompletos para cadastro", true, HttpStatus.UNPROCESSABLE_CONTENT);
        }

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
            .orElseThrow(() -> new BusinessException("Categoria não encontrada", true, HttpStatus.NOT_FOUND));

        Subcategoria subcategoria = new Subcategoria();
        subcategoria.setNome(dto.getNome());
        subcategoria.setSlug(dto.getSlug());
        subcategoria.setCategoria(categoria);

        return repository.save(subcategoria);
    }

    public Subcategoria findBySlug(String slug) {
        return repository.findBySlug(slug)
            .orElseThrow(() -> new BusinessException("Subcategoria não encontrada", true, HttpStatus.NOT_FOUND));
    }

    public Subcategoria update(Long id, SubcategoriaRequestDTO dto) {
        Subcategoria subcategoria = repository.findById(id)
            .orElseThrow(() -> new BusinessException("Subcategoria não encontrada", true, HttpStatus.NOT_FOUND));

        if (dto.getNome() != null) subcategoria.setNome(dto.getNome());
        if (dto.getSlug() != null) subcategoria.setSlug(dto.getSlug());

        if (dto.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new BusinessException("Categoria não encontrada", true, HttpStatus.NOT_FOUND));
            subcategoria.setCategoria(categoria);
        }

        return repository.save(subcategoria);
    }

    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new BusinessException("Subcategoria não encontrada", true, HttpStatus.NOT_FOUND);
        repository.deleteById(id);
    }
}
