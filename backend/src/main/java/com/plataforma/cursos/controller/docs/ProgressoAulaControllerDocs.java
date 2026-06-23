package com.plataforma.cursos.controller.docs;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import java.util.List;
import com.plataforma.cursos.domain.entities.ProgressoAula;
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

    /**
     * Obter o progresso de uma aula específica de um usuário autenticado.
     * @param aulaId Id da aula para a qual o processo deve ser obtido.
     * @param authentication Objeto de autenticação JWT, que contém informações sobre o usuário autenticado.
     * @return ResponseEntity contendo o progresso da aula ou uma resposta de erro caso o usuário não seja encontrado.
     */
    @Operation(summary = "Obter progresso de uma aula", description = "Retorna o progresso do usuário para uma aula específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Progresso obtido com sucesso", content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = ProgressoAula.class))
            )),
    })
    public ResponseEntity<ProgressoAula> getProgresso( @PathVariable Long aulaId, Authentication authentication);


    /**
     * Salva o progresso do usuário em uma aula
     * @param progresso {@link ProgressoRequestDTO}, DTO que contém todas as informações sobre a aula e o progresso do usuário.
     * @param authentication Objeto de autenticação JWT, que contém informações sobre o usuário autenticado.
     */
    @Operation(summary = "Salvar progresso de uma aula", description = "Salva o progresso do usuário para uma aula específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Progresso salvo com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário ou aula não encontrados")
    })
    public ResponseEntity<?> salvar(@RequestBody ProgressoRequestDTO progresso, Authentication authentication);

    /**
     * Obter a última aula assistida pelo usuário em um curso específico.
     * @param cursoId ID do curso para o qual se deseja obter a última aula assistida.
     * @param authentication Objeto de autenticação JWT, que contém informações sobre o usuário autenticado.
     * @return {@link ProgressoAula}, contendo as informações sobre a última aula assistida.
     */
    @Operation(summary = "Obter última aula assistida", description = "Retorna a última aula assistida pelo usuário em um curso específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Última aula obtida com sucesso (ou nenhuma aula assistida)"),
    })
    public ProgressoAula getUltimaAula(@PathVariable Integer cursoId, Authentication authentication);

    /**
     * Obter a lssta de IDs das aulas concluídas pelo usuário em um curso específico.
     * @param cursoId ID do curso para qual se deseja obter as aulas concluídas pelo usuário.
     * @param authentication Objeto de autenticação JWT, que contém informações sobre o usuário autenticado.
     * @return Lista de IDs das aulas concluídas pelo usuário no curso selecionado. Se o usuário não tiver concluído nenhuma aula, a lista será vazia.
     */
    @Operation(summary = "Obter aulas concluídas", description = "Retorna a lista de IDs das aulas concluídas pelo usuário em um curso específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aulas concluídas obtidas com sucesso (ou nenhuma aula concluída)"),
    })
    public List<Integer> getAulasConcluidas(@PathVariable Integer cursoId, Authentication authentication);
}