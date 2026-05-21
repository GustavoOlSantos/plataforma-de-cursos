package com.plataforma.cursos.controller.docs;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import com.plataforma.cursos.domain.entities.Cursos;
import com.plataforma.cursos.DTO.CursosDTO;
import com.plataforma.cursos.DTO.ViewCursosDTO;
import com.plataforma.cursos.DTO.CriarCursoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;

@Tag(name = "Cursos", description = "Gerenciamento de cursos")
public interface CursosControllerDocs {

    @Operation(summary = "Encontrar todos cursos", description = "Encontra todos os cursos cadastrados")
    @ApiResponses({
        @ApiResponse(responseCode = "200", 
            description = "Lista de cursos retornada com sucesso",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = CursosDTO.class))
            )
        ),
        @ApiResponse(responseCode = "403", description = "não autorizado"),
    })
    public List<CursosDTO> list();

    @Operation(summary = "Encontrar mais vendidos", description = "Encontra os cursos mais vendidos")
    @ApiResponses({
        @ApiResponse(responseCode = "200", 
            description = "Lista de cursos retornada com sucesso",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = CursosDTO.class))
            )
        ),
        @ApiResponse(responseCode = "404", description = "nenhum curso encontrado"),
    })
    public List<CursosDTO> getMaisVendidos();


    @Operation(summary = "Encontrar cursos por nome", description = "Encontra cursos pelo nome")
    @ApiResponses({
        @ApiResponse(responseCode = "200", 
            description = "Lista de cursos retornada com sucesso",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = CursosDTO.class))
            )
        ),
        @ApiResponse(responseCode = "404", description = "nenhum curso encontrado"),
    })
    public List<CursosDTO> getByName(@PathVariable String name);

    @Operation(summary = "Encontrar cursos por slug", description = "Encontra cursos pelo slug")
    @ApiResponses({
        @ApiResponse(responseCode = "200", 
            description = "Lista de cursos retornada com sucesso",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = CursosDTO.class))
            )
        ),
        @ApiResponse(responseCode = "404", description = "nenhum curso encontrado"),
    })
    public List<CursosDTO> getBySlug(@PathVariable String slug);

    @Operation(summary = "Encontrar curso por ID", description = "Encontra um curso pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", 
            description = "Curso encontrado com sucesso",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = CursosDTO.class)
            )
        ),
        @ApiResponse(responseCode = "404", description = "curso não encontrado"),
    })
    public CursosDTO getById(@PathVariable Long id);

    @Operation(summary = "Encontrar aulas por slug", description = "Encontra as aulas de um curso pelo slug")
    @ApiResponses({
        @ApiResponse(responseCode = "200", 
            description = "Lista de aulas retornada com sucesso",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = ViewCursosDTO.class))
            )
        ),
        @ApiResponse(responseCode = "404", description = "Nenhum curso encontrado"),
    })
    public List<ViewCursosDTO> getAulasBySlug(@PathVariable String slug);

    @Operation(summary = "Registrar novo curso", description = "Registra um novo curso")
    @ApiResponses({
        @ApiResponse(responseCode = "201", 
            description = "Curso registrado com sucesso",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = CursosDTO.class)
            )
        ),
        @ApiResponse(responseCode = "404", description = "Subcategoria informada não encontrada: {idSubcategoria}"),
    })
    public ResponseEntity<CursosDTO> create(@RequestBody CriarCursoDTO dto);

    @Operation(summary = "Atualizar curso", description = "Atualiza um curso existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", 
            description = "Curso atualizado com sucesso",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = CursosDTO.class)
            )
        ),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado"),
    })
    public ResponseEntity<CursosDTO> update(@RequestBody Cursos curso, @PathVariable Long id);

    @Operation(summary = "Deletar curso", description = "Deleta um curso existente")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Curso deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado"),
    })
    public ResponseEntity<Void> delete(@PathVariable Long id);
}