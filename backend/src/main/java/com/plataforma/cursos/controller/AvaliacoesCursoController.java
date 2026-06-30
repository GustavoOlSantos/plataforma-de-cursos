package com.plataforma.cursos.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.plataforma.cursos.domain.documents.AvaliacoesCurso;
import com.plataforma.cursos.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import com.plataforma.cursos.service.AvaliacoesCursoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.plataforma.cursos.controller.docs.AvaliacoesCursoControllerDocs;

/**
 * Esse {@link RestController} é responsável por gerenciar as operações relacionadas às avaliações dos cursos, incluindo cadastro e consulta.
 * A implementação de documentação da API é feita através da interface {@link AvaliacoesCursoControllerDocs}, que define os contratos de cada endpoint.
 * 
 * @author Gustavo Santos
 */
@RestController
@RequestMapping("/avaliacoes/curso")
public class AvaliacoesCursoController implements AvaliacoesCursoControllerDocs {

    private final AvaliacoesCursoService service;

    public AvaliacoesCursoController(AvaliacoesCursoService service) {
        this.service = service;
    }

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    public AvaliacoesCurso create(@RequestBody AvaliacoesCurso avaliacao, Authentication authentication) {
        if(authentication == null || authentication.getName() == null) {
            throw new BusinessException("Usuário não autenticado", true, HttpStatus.UNAUTHORIZED, "create-avaliacoes");
        }
        
        int userId = Integer.parseInt(authentication.getName());
        return service.register(avaliacao, userId);
    }


    @GetMapping("id-curso/{id}")
    public List<AvaliacoesCurso> obterAvaliacoes(@PathVariable Integer id) {
        return service.findByCursoId(id);
    }
}