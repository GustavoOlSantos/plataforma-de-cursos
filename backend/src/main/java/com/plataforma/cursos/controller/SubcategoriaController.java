package com.plataforma.cursos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.plataforma.cursos.DTO.SubcategoriaRequestDTO;
import com.plataforma.cursos.domain.entities.Subcategoria;
import com.plataforma.cursos.service.SubcategoriaService;
import com.plataforma.cursos.controller.docs.SubcategoriaControllerDocs;


/**
 * Esse {@link RestController} é responsável por gerenciar as operações relacionadas às subcategorias, incluindo cadastro, atualização e remoção.
 * A implementação de documentação da API é feita através da interface {@link SubcategoriaControllerDocs}, que define os contratos de cada endpoint.
 * 
 * @author Gustavo Santos
 */
@RestController
@RequestMapping("/subcategorias")
public class SubcategoriaController implements SubcategoriaControllerDocs {

    @Autowired
    private SubcategoriaService service;

    @GetMapping("/slug/{slug}")
    public ResponseEntity<Subcategoria> findBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(service.findBySlug(slug));
    }
    @PostMapping
    public ResponseEntity<Subcategoria> save(@RequestBody SubcategoriaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Subcategoria> update(@PathVariable Long id, @RequestBody SubcategoriaRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
