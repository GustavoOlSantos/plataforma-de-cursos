package com.plataforma.cursos.controller.docs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.plataforma.cursos.domain.entities.Categoria;
import com.plataforma.cursos.DTO.CategoriaDTO;
import com.plataforma.cursos.DTO.CategoriaRequestDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.*;

@Tag(name = "Categorias", description = "Gerenciamento de categorias de cursos")
public interface CategoriaControllerDocs {

    /**
     * Endpoint para encontrar todas as categorias cadastradas.
     * @return Lista de categorias.
     */
    @Operation(summary = "Encontrar todos categorias", description = "Encontra todas as categorias cadastradas")
    public List<CategoriaDTO> list();

    /**
     * Endpoint para buscar categorias filtradas com base em uma lista de nomes. Retorna as categorias correspondentes aos nomes fornecidos.
     * @param nomes Lista de nomes de categorias a serem filtradas.
     * @return Lista de categorias correspondentes aos nomes fornecidos.
     */
    @Operation(summary = "Filtrar categorias", description = "Filtra categorias com base em uma lista de nomes")
    public List<CategoriaDTO> buscarCategoriasFiltradasFooter(@RequestBody List<String> nomes);

    /**
     * Endpoint para criar uma nova categoria. Recebe um objeto CategoriaRequestDTO contendo os dados da categoria a ser criada e retorna a categoria criada.
     * @param dto Objeto CategoriaRequestDTO contendo os dados da categoria a ser criada.
     * @return ResponseEntity com a categoria criada.
     */
    @Operation(summary = "Criar categoria", description = "Cria uma nova categoria")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso"),
        @ApiResponse(responseCode = "422", description = "Dados incompletos para cadastro"),
    })
    public ResponseEntity<Categoria> save(@RequestBody CategoriaRequestDTO dto);

    /**
     * Endpoint para atualizar uma categoria existente. Recebe o ID da categoria a ser atualizada e um objeto CategoriaRequestDTO contendo os novos dados.
     * @param id ID da categoria a ser atualizada.
     * @param dto Objeto CategoriaRequestDTO contendo os novos dados da categoria.
     * @return ResponseEntity com a categoria atualizada.
     */
    @Operation(summary = "Atualizar categoria", description = "Atualiza uma categoria existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody CategoriaRequestDTO dto);

    /**
     * Endpoint para excluir uma categoria existente. Recebe o ID da categoria a ser excluída.
     * @param id ID da categoria a ser excluída.
     * @return ResponseEntity com o status da operação de exclusão.
     */
    @Operation(summary = "Excluir categoria", description = "Exclui uma categoria existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Categoria excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id);
}