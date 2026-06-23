package com.plataforma.cursos.domain.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import com.plataforma.cursos.domain.entities.User;
import com.plataforma.cursos.domain.entities.Cursos;
    
@Getter
@Setter
@SuppressWarnings("unused")
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
    
    public LocalDateTime getDataCompra(){
        return data_compra;
    }

     public void setDataCompra(LocalDateTime data_compra){
        this.data_compra = data_compra;
    }
}