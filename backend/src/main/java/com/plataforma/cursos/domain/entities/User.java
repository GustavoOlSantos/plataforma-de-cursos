package com.plataforma.cursos.domain.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
    
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private String nome;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String telefone;
    private String userImagePath;

    public User() {}
    
    public User(String nome, String email, String password, String telefone, String userImagePath) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.telefone = telefone;
        this.userImagePath = userImagePath;
    }

    public User(Long id, String nome, String email, String password, String telefone, String userImagePath) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.telefone = telefone;
        this.userImagePath = userImagePath;
    }
}