package com.plataforma.cursos.controller.docs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.plataforma.cursos.DTO.SubcategoriaRequestDTO;
import com.plataforma.cursos.domain.entities.Subcategoria;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.*;

@Tag(name = "Subcategorias", description = "Gerenciamento de subcategorias de cursos")
public interface SubcategoriaControllerDocs {

    @Operation(summary = "Encontrar subcategoria por slug", description = "Encontra uma subcategoria pelo slug")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Subcategoria encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Subcategoria não encontrada")
    })
    public ResponseEntity<Subcategoria> findBySlug(@PathVariable String slug);

    @Operation(summary = "Criar subcategoria", description = "Cria uma nova subcategoria")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Subcategoria criada com sucesso"),
        @ApiResponse(responseCode = "422", description = "Dados incompletos para cadastro"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    public ResponseEntity<Subcategoria> save(@RequestBody SubcategoriaRequestDTO dto);

    @Operation(summary = "Atualizar subcategoria", description = "Atualiza uma subcategoria existente")
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Subcategoria atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Subcategoria não encontrada")
    })
    public ResponseEntity<Subcategoria> update(@PathVariable Long id, @RequestBody SubcategoriaRequestDTO dto);

    @Operation(summary = "Excluir subcategoria", description = "Exclui uma subcategoria existente")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Subcategoria excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Subcategoria não encontrada")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id);
}
