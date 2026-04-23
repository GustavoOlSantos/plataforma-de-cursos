package com.plataforma.cursos.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.plataforma.cursos.domain.User;
import com.plataforma.cursos.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> list() {
        return service.findAll();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return service.save(user);
    }
}