package com.plataforma.cursos.domain.entities;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import com.plataforma.cursos.domain.entities.User;
import com.plataforma.cursos.domain.entities.Cursos;
    
@Entity
@Table( name = "compras",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "curso_id"})
            }
    )
public class Compra{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Cursos curso;
    private LocalDateTime  data_compra;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public User getUsuario(){
        return usuario;
    }

    public void setUsuario(User usuario){
        this.usuario = usuario;
    }

    public Cursos getCurso(){
        return curso;
    }

    public void setCurso(Cursos curso){
        this.curso = curso;
    }  

    public LocalDateTime getDataCompra(){
        return data_compra;
    }

     public void setDataCompra(LocalDateTime data_compra){
        this.data_compra = data_compra;
    }
}