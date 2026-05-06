package com.plataforma.cursos.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.plataforma.cursos.domain.Categoria;
import com.plataforma.cursos.DTO.CategoriaDTO;
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
}