package com.plataforma.cursos.controller.docs;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.plataforma.cursos.domain.documents.AvaliacoesCurso;
import org.springframework.security.core.Authentication;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.media.*;

@Tag(name = "Avaliações", description = "Gerenciamento de avaliações de cursos")
@SecurityRequirement(name = "bearerAuth")
public interface AvaliacoesCursoControllerDocs {

    @Operation(summary = "Registrar nova avaliação", description = "Registra uma nova avaliação para um curso")
    @ApiResponses({
        @ApiResponse(responseCode = "201", 
            description = "Avaliação registrada com sucesso",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = AvaliacoesCurso.class)
            )
        ),
        @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
        @ApiResponse(responseCode = "422", description = "Preencha a sua avaliação"),
        @ApiResponse(responseCode = "422", description = "A nota deve ser entre 1 e 5"),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado"),
    })
    public AvaliacoesCurso create(@RequestBody AvaliacoesCurso avaliacao, Authentication authentication);

    @Operation(summary = "Obter avaliações por curso", description = "Obtém todas as avaliações de um curso específico")
    @ApiResponses({
        @ApiResponse(responseCode = "200", 
            description = "Avaliações retornadas com sucesso",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = AvaliacoesCurso.class))
            )
        )
    })
    public List<AvaliacoesCurso> obterAvaliacoes(@PathVariable Integer id);
}