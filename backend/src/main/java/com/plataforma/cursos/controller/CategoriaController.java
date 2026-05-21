package com.plataforma.cursos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.plataforma.cursos.domain.entities.Categoria;
import com.plataforma.cursos.DTO.CategoriaDTO;
import com.plataforma.cursos.DTO.CategoriaRequestDTO;
import com.plataforma.cursos.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoriaDTO> list() {
        return service.findAll();
    }

    @PostMapping("/filtrar")
    public List<CategoriaDTO> buscarCategoriasFiltradasFooter(@RequestBody List<String> nomes) {
        return service.buscarCategoriasFiltradasFooter(nomes);
    }

    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody CategoriaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody CategoriaRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}