package com.plataforma.cursos.service;

import org.springframework.stereotype.Service;
import org.springframework.http.*;
import java.util.List;
import com.plataforma.cursos.domain.entities.User;
import com.plataforma.cursos.DTO.UserDTO;
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

    public List<UserDTO> findAll() {
        List<User> users = repository.findAll();

        return users.stream()
        .map(UserDTO::fromEntity)
        .toList();
    }

    public UserDTO findById(Long id) {
        User user = repository.findById(id)
        .orElseThrow(() -> new BusinessException("Usuário não encontrado", true, HttpStatus.NOT_FOUND, "find-user"));

        return UserDTO.fromEntity(user);
    }

    public String login(String email, String password) {

        if(email == null || email.isBlank() || password == null || password.isBlank()){
            throw new BusinessException("Dados incompletos para login", true, HttpStatus.UNPROCESSABLE_CONTENT, "login-user");
        }

        User user = repository.findByEmail(email);

        if (user == null || !passwordService.matches(password, user.getPassword())) {
            throw new BusinessException("Credenciais inválidas", true, HttpStatus.BAD_REQUEST, "login-user");
        }

        return tokenService.generateToken(user);
    }

    public User register(User user) {
        if(user.getNome() == null || user.getNome().isBlank() ||
            user.getEmail() == null || user.getEmail().isBlank() ||
            user.getPassword() == null || user.getPassword().isBlank()){
            throw new BusinessException("Dados incompletos para cadastro", true,HttpStatus.UNPROCESSABLE_CONTENT, "create-user");
        }

        if(repository.findByEmail(user.getEmail()) != null){
            throw new BusinessException("Usuário já cadastrado no sistema", false, HttpStatus.BAD_REQUEST, "create-user");
        }

        user.setPassword(passwordService.hash(user.getPassword()));
        return repository.save(user);
    }
}