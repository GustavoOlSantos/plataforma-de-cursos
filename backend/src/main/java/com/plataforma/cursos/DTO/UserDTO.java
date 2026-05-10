package com.plataforma.cursos.DTO;

import com.plataforma.cursos.domain.entities.User;

public class UserDTO {

    public Long  id;
    public String nome;
    public String email;
    public String telefone;
    public String userImagePath;

    public UserDTO() {  
    }

    public UserDTO(Long id, String nome, String email, String telefone, String userImagePath) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.userImagePath = userImagePath;
    }

    public static UserDTO fromEntity(User user) {
        UserDTO dto = new UserDTO();

        dto.id = user.getId();
        dto.nome = user.getNome();
        dto.email = user.getEmail();
        dto.telefone = user.getTelefone();
        dto.userImagePath = user.getUserImagePath();

        return dto;
    }


    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; } 
    public String getUserImagePath() { return userImagePath; }
}