package com.plataforma.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.plataforma.cursos.domain.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}