package com.plataforma.cursos.controller.docs;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import java.util.List;
import com.plataforma.cursos.domain.entities.ProgressoAula;
import com.plataforma.cursos.DTO.CursosDTO;
import com.plataforma.cursos.DTO.ProgressoRequestDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Progresso de Aula", description = "Endpoints para gerenciar o progresso das aulas")
public interface ProgressoAulaControllerDocs {

    @Operation(summary = "Obter progresso de uma aula", description = "Retorna o progresso do usuário para uma aula específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Progresso obtido com sucesso", content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = ProgressoAula.class))
            )),
    })
    public ResponseEntity<ProgressoAula> getProgresso( @PathVariable Long aulaId, Authentication authentication);


    @Operation(summary = "Salvar progresso de uma aula", description = "Salva o progresso do usuário para uma aula específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Progresso salvo com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário ou aula não encontrados")
    })
    public ResponseEntity<?> salvar(@RequestBody ProgressoRequestDTO progresso, Authentication authentication);

    @Operation(summary = "Obter última aula assistida", description = "Retorna a última aula assistida pelo usuário em um curso específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Última aula obtida com sucesso (ou nenhuma aula assistida)"),
    })
    public ProgressoAula getUltimaAula(@PathVariable Integer cursoId, Authentication authentication);

    @Operation(summary = "Obter aulas concluídas", description = "Retorna a lista de IDs das aulas concluídas pelo usuário em um curso específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aulas concluídas obtidas com sucesso (ou nenhuma aula concluída)"),
    })
    public List<Integer> getAulasConcluidas(@PathVariable Integer cursoId, Authentication authentication);
}