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

    /**
     * Endpoint para obter a lista de cursos comprados por um usuário específico.
     * @param authentication
     * @return
     */
    @Operation(summary = "Obter cursos comprados por usuário", description = "Retorna a lista de cursos comprados por um usuário específico")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cursos retornados com sucesso"),
        @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    })
    public List<CursosDTO> getCursoByUserId(Authentication authentication);

    /**
     * Endpoint para permitir que um usuário compre um curso específico. Se o usuário já comprou,não estiver autenticado ou se o curso não existir, retornará uma resposta apropriada.
     * @param cursoId Id do curso a ser comprado.
     * @param authentication Objeto de autenticação do usuário que está tentando comprar o curso.
     * @return ResponseEntity com o status da operação de compra.
     */
    @Operation(summary = "Comprar curso", description = "Permite que um usuário compre um curso")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Curso comprado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Esse usuário já comprou este curso"),
        @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
        @ApiResponse(responseCode = "404", description = "Usuário ou curso não encontrado")
    })
    public ResponseEntity<?> comprarCurso(@PathVariable Long cursoId, Authentication authentication);

    /**
     * Endpoint para verificar se um usuário específico já comprou um curso. Retorna verdadeiro se o usuário já comprou o curso, caso contrário, retorna falso. Se o usuário não estiver autenticado ou se o curso não existir, retornará uma resposta apropriada.
     * @param cursoId Id do curso a ser verificado.
     * @param authentication Objeto de autenticação do usuário que está tentando verificar a compra do curso.
     * @return boolean indicando se o usuário já comprou o curso.
     */
    @Operation(summary = "Verificar se usuário já comprou o curso", description = "Verifica se um usuário específico já comprou um curso")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Verificação concluída com sucesso"),
        @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
        @ApiResponse(responseCode = "404", description = "Usuário ou curso não encontrado")
    })
    public boolean jaComprou(@PathVariable Long cursoId, Authentication authentication);
}