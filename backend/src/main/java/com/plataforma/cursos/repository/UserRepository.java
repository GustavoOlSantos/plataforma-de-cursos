package com.plataforma.cursos.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.plataforma.cursos.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}