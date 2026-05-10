package com.plataforma.cursos.service;

import org.springframework.stereotype.Service;
import org.springframework.http.*;
import java.util.List;
import com.plataforma.cursos.domain.entities.User;
import com.plataforma.cursos.security.service.TokenService;
import com.plataforma.cursos.security.service.PasswordService;
import com.plataforma.cursos.exception.BusinessException;
import com.plataforma.cursos.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordService passwordService;
    private final TokenService tokenService;

    public UserService(UserRepository repository, PasswordService passwordService, TokenService tokenService) {
        this.repository = repository;
        this.passwordService = passwordService;
        this.tokenService = tokenService;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public String login(String email, String password) {
        User user = repository.findByEmail(email);

        if (user == null || !passwordService.matches(password, user.getPassword())) {
            throw new BusinessException("Credenciais inválidas", true, HttpStatus.BAD_REQUEST);
        }

        return tokenService.generateToken(user);
    }

    public User register(User user) {
        if(user.getNome() == null || user.getEmail() == null || user.getPassword() == null){
            throw new BusinessException("Dados incompletos para cadastro", true,HttpStatus.BAD_REQUEST);
        }

        if(repository.findByEmail(user.getEmail()) != null){
            throw new BusinessException("Usuário já cadastrado no sistema", false, HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordService.hash(user.getPassword()));
        return repository.save(user);
    }
}