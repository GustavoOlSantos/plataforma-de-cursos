package com.plataforma.cursos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.plataforma.cursos.domain.entities.User;
import com.plataforma.cursos.DTO.UserDTO;
import com.plataforma.cursos.service.UserService;
import com.plataforma.cursos.controller.docs.UserControllerDocs;

/**
 * Esse {@link RestController} é responsável por gerenciar as operações relacionadas aos usuários, incluindo cadastro, login 
 * e recuperação de informações do usuário.
 * A implementação de documentação da API é feita através da interface {@link UserControllerDocs}, que define os contratos de cada endpoint.
 * @author Gustavo Santos
 */
@RestController
@RequestMapping("/auth")
public class UserController implements UserControllerDocs {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserDTO> list() {
        return service.findAll();
    }
    
    @GetMapping("/id/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.register(user));
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return service.login(user.getEmail(), user.getPassword());
    }
}