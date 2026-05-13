package com.plataforma.cursos.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import com.plataforma.cursos.domain.entities.Compra;
import com.plataforma.cursos.service.CompraService;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping("/{cursoId}")
    public ResponseEntity<?> comprarCurso(@PathVariable Long cursoId, Authentication authentication) {
        Long userId = Long.parseLong(authentication.getName());
        compraService.comprarCurso(userId, cursoId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{cursoId}")
    public boolean jaComprou(@PathVariable Long cursoId, Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        return compraService.jaComprouCurso(userId, cursoId);

    }
}