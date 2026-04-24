package com.plataforma.cursos.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.plataforma.cursos.domain.User;
import com.plataforma.cursos.service.security.TokenService;
import com.plataforma.cursos.exception.BusinessException;
import com.plataforma.cursos.repository.UserRepository;
import com.plataforma.cursos.service.PasswordService;

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
            throw new BusinessException("Credenciais inválidas", true);
        }

        return tokenService.generateToken(user.getEmail());
    }

    public User save(User user) {
        if(user.getNome() == null || user.getEmail() == null || user.getPassword() == null){
            throw new BusinessException("Dados incompletos para cadastro", true);
        }

        if(repository.findByEmail(user.getEmail()) != null){
            throw new BusinessException("Usuário já cadastrado no sistema", false);
        }

        user.setPassword(passwordService.hash(user.getPassword()));
        return repository.save(user);
    }
}