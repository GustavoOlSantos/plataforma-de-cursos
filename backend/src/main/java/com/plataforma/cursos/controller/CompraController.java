package com.plataforma.cursos.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import com.plataforma.cursos.DTO.CursosDTO;
import com.plataforma.cursos.exception.BusinessException;
import com.plataforma.cursos.service.CompraService;
import com.plataforma.cursos.controller.docs.CompraControllerDocs;

@RestController
@RequestMapping("/compras")
public class CompraController implements CompraControllerDocs {

    @Autowired
    private CompraService compraService;

    @GetMapping
    public List<CursosDTO> getCursoByUserId(Authentication authentication){
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BusinessException("Usuário não autenticado", true, HttpStatus.UNAUTHORIZED);
        }

        Long userId = Long.parseLong(authentication.getName());
        return compraService.getCursoByUserId(userId);
    }

    @PostMapping("/{cursoId}")
    public ResponseEntity<?> comprarCurso(@PathVariable Long cursoId, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BusinessException("Usuário não autenticado", true, HttpStatus.UNAUTHORIZED);
        }

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