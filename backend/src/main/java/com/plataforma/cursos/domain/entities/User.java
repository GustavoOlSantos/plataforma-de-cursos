package com.plataforma.cursos.domain.entities;
import jakarta.persistence.*;
    
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private String nome;
    private String email;
    private String password;
    private String telefone;

    public User() {}
    
    public User(String nome, String email, String password, String telefone) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.telefone = telefone;
    }

    public User(Long id, String nome, String email, String password, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.telefone = telefone;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getTelefone() { return telefone; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}