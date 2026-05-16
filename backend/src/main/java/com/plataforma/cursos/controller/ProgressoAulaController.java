package com.plataforma.cursos.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

import com.plataforma.cursos.domain.entities.ProgressoAula;
import com.plataforma.cursos.DTO.ProgressoRequestDTO;
import com.plataforma.cursos.service.ProgressoAulaService;

@RestController
@RequestMapping("/progresso")
public class ProgressoAulaController {

    private final ProgressoAulaService service;

    public ProgressoAulaController(ProgressoAulaService service) {
        this.service = service;
    }

    @GetMapping("/aula/{aulaId}")
    public ResponseEntity<ProgressoAula> getProgresso( @PathVariable Long aulaId, Authentication authentication) {
        Long userId = Long.parseLong(authentication.getName());

        ProgressoAula progresso = service.getProgresso(aulaId, userId);
        return ResponseEntity.ok(progresso);
    }

    @PostMapping("/aula")
    public ResponseEntity<?> salvar(@RequestBody ProgressoRequestDTO progresso, Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());

        service.salvar(progresso, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ultima-aula/{cursoId}")
    public ProgressoAula getUltimaAula(@PathVariable Integer cursoId, Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());

        return service.getUltimaAulaAssistida(userId, cursoId);
    }

    @GetMapping("/aulas-concluidas/{cursoId}")
    public List<Integer> getAulasConcluidas(@PathVariable Integer cursoId, Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());

        return service.getAulasConcluidas(userId, cursoId);
    }
}