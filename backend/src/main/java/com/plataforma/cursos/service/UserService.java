package com.plataforma.cursos.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.plataforma.cursos.domain.User;
import com.plataforma.cursos.exception.BusinessException;
import com.plataforma.cursos.repository.UserRepository;
import com.plataforma.cursos.service.PasswordService;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordService passwordService;

    public UserService(UserRepository repository, PasswordService passwordService) {
        this.repository = repository;
        this.passwordService = passwordService;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public String login(String email, String password) {
        User user = repository.findByEmail(email);

        if (user == null || !passwordService.matches(password, user.getPassword())) {
            throw new BusinessException("Email ou senha inválidos");
        }

        return "Funcionou!";
    }

    public User save(User user) {
        if(user.getNome() == null || user.getEmail() == null || user.getPassword() == null){
             throw new BusinessException("Dados incompletos para cadastro");
        }

        if(repository.findByEmail(user.getEmail()) != null){
            throw new BusinessException("Erro ao processar cadastro");
        }

        user.setPassword(passwordService.hash(user.getPassword()));
        return repository.save(user);
    }
}