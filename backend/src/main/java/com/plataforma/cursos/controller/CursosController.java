package com.plataforma.cursos.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import com.plataforma.cursos.domain.entities.Cursos;
import com.plataforma.cursos.DTO.CursosDTO;
import com.plataforma.cursos.service.CursosService;

@RestController
@RequestMapping("/cursos")
public class CursosController {

    private final CursosService service;

    public CursosController(CursosService service) {
        this.service = service;
    }

    @GetMapping
    public List<CursosDTO> list() {
        return service.findAll();
    }

    @GetMapping("/maisVendidos")
    public List<CursosDTO> getMaisVendidos() {
        return service.findBestSellers();
    }

    @GetMapping("/nome/{name}")
    public List<CursosDTO> getByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @GetMapping("/slug/{slug}")
    public List<CursosDTO> getBySlug(@PathVariable String slug) {
        return service.findBySlug(slug);
    }

    @GetMapping("/id/{id}")
    public CursosDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/registrar")
    public Cursos create(@RequestBody Cursos curso) {
        return service.register(curso);
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<CursosDTO> update(@RequestBody Cursos curso, @PathVariable Long id) {
        CursosDTO cursoAtualizado = service.update(id, curso);
        return ResponseEntity.ok(cursoAtualizado);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}