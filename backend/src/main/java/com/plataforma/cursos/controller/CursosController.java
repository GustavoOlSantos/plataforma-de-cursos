package com.plataforma.cursos.controller;

import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import com.plataforma.cursos.domain.Cursos;
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

    @GetMapping("/id/{id}")
    public CursosDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/registrar")
    public Cursos create(@RequestBody Cursos curso) {
        return service.register(curso);
    }
}