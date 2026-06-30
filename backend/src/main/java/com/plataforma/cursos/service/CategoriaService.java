package com.plataforma.cursos.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.plataforma.cursos.domain.entities.Categoria;
import com.plataforma.cursos.DTO.CategoriaDTO;
import com.plataforma.cursos.DTO.CategoriaRequestDTO;
import com.plataforma.cursos.DTO.SubcategoriaDTO;
import com.plataforma.cursos.exception.BusinessException;
import com.plataforma.cursos.repository.CategoriaRepository;
import org.springframework.http.HttpStatus;

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

    public Categoria save(CategoriaRequestDTO dto) {

        if(dto.getNome() == null || dto.getNome().isEmpty() || dto.getSlug() == null || dto.getSlug().isEmpty()){
            throw new BusinessException("Dados incompletos para cadastro", true, HttpStatus.UNPROCESSABLE_CONTENT, "create_categorias");
        }

        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        categoria.setSlug(dto.getSlug());
        return repository.save(categoria);
    }

    public Categoria update(Long id, CategoriaRequestDTO dto) {
        Categoria categoria = repository.findById(id)
            .orElseThrow(() -> new BusinessException("Categoria não encontrada", true, HttpStatus.NOT_FOUND, "update_categorias"));

        if (dto.getNome() != null) categoria.setNome(dto.getNome());
        if (dto.getSlug() != null) categoria.setSlug(dto.getSlug());

        return repository.save(categoria);
    }

    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new BusinessException("Categoria não encontrada", true, HttpStatus.NOT_FOUND, "delete-categorias");
        repository.deleteById(id);
    }
}