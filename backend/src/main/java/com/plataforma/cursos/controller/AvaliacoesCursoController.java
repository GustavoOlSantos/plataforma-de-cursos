package com.plataforma.cursos.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.plataforma.cursos.domain.documents.AvaliacoesCurso;
import com.plataforma.cursos.service.AvaliacoesCursoService;

@RestController
@RequestMapping("/avaliacoes/curso")
public class AvaliacoesCursoController {

    private final AvaliacoesCursoService service;

    public AvaliacoesCursoController(AvaliacoesCursoService service) {
        this.service = service;
    }

    @PostMapping
    public AvaliacoesCurso create(@RequestBody AvaliacoesCurso avaliacao) {
        return service.register(avaliacao);
    }

    @GetMapping("id-curso/{id}")
    public List<AvaliacoesCurso> obterAvaliacoes(@PathVariable Integer id) {
        return service.findByCursoId(id);
    }
}