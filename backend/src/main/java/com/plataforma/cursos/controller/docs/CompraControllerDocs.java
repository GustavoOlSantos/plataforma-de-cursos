package com.plataforma.cursos.controller.docs;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import com.plataforma.cursos.DTO.CursosDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.*;

@Tag(name = "Compras", description = "Gerenciamento de compras de cursos")
public interface CompraControllerDocs {

    @Operation(summary = "Obter cursos comprados por usuário", description = "Retorna a lista de cursos comprados por um usuário específico")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cursos retornados com sucesso"),
        @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    })
    public List<CursosDTO> getCursoByUserId(Authentication authentication);

    @Operation(summary = "Comprar curso", description = "Permite que um usuário compre um curso")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Curso comprado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Esse usuário já comprou este curso"),
        @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
        @ApiResponse(responseCode = "404", description = "Usuário ou curso não encontrado")
    })
    public ResponseEntity<?> comprarCurso(@PathVariable Long cursoId, Authentication authentication);

    @Operation(summary = "Verificar se usuário já comprou o curso", description = "Verifica se um usuário específico já comprou um curso")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Verificação concluída com sucesso"),
        @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
        @ApiResponse(responseCode = "404", description = "Usuário ou curso não encontrado")
    })
    public boolean jaComprou(@PathVariable Long cursoId, Authentication authentication);
}