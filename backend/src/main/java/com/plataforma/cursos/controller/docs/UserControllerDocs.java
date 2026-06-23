package com.plataforma.cursos.controller.docs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.plataforma.cursos.domain.entities.User;
import com.plataforma.cursos.DTO.UserDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users", description = "Gerenciamento de usuários")
public interface UserControllerDocs {

    /**
     * Encontra todos os usuários cadastrados (rota protegida).
     * @return Lista de usuários cadastrados.
     */
    @Operation(summary = "Encontrar todos usuários", description = "Encontra todos os usuários cadastrados (rota protegida)")
    @ApiResponses({
        @ApiResponse(responseCode = "403", description = "não autorizado"),
    })
    public List<UserDTO> list();

    /**
     * Encontra um usuário pelo ID.
     * @param id Id do usuário a ser encontrado.
     * @return {@link UserDTO}, contendo as informações do usuário encontrado.
     */
    @Operation(summary = "Encontrar usuário", description = "Encontra um usuário pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public UserDTO findById(@PathVariable Long id);

    /**
     * Cria um novo usuário.
     * @param user {@link User}, contendo as informações do usuário a ser criado.
     * @return {@link ResponseEntity<User>}, contendo o usuário criado.
     */
    @Operation(summary = "Criar usuário", description = "Cria um novo usuário")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
        @ApiResponse(responseCode = "422", description = "Dados incompletos para cadastro"),
        @ApiResponse(responseCode = "400", description = "Não foi possível completar a operação")
    })
    public ResponseEntity<User> create(@RequestBody User user);

    /**
     * Realiza o login do usuário.
     * @param user {@link User}, contendo as informações do usuário a ser autenticado.
     * @return {@link String}, contendo o token JWT.
     */
    @Operation(summary = "Login do usuário", description = "Realiza o login do usuário")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "{token jwt}"),
        @ApiResponse(responseCode = "422", description = "Dados incompletos para login"),
        @ApiResponse(responseCode = "400", description = "Credenciais inválidas")
    })
    public String login(@RequestBody User user);
}