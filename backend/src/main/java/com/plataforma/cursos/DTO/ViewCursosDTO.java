package com.plataforma.cursos.DTO;
import java.util.List;
import java.util.Date;
import com.plataforma.cursos.domain.entities.Cursos;
import com.plataforma.cursos.DTO.ModuloCursoDTO;

@SuppressWarnings("unused")
public class ViewCursosDTO {
    public Long id;
    public String nome;
    public String descricao;
    public String instrutor;
    public String duracao;
    public int numeroAulas;
    public Date ultimaAtualizacao;
    public int alunosMatriculados;
    public String nivel;
    public List<ModuloCursoDTO> modulos;

    public ViewCursosDTO() {  
    }

    public static ViewCursosDTO fromEntity(Cursos curso) {
        ViewCursosDTO dto = new ViewCursosDTO();
        dto.id = curso.getId();
        dto.nome = curso.getNome();
        dto.descricao = curso.getDescricao();           
        dto.instrutor = curso.getInstrutor();
        dto.duracao = curso.getDuracao();
        dto.numeroAulas = curso.getNumeroAulas();
        dto.ultimaAtualizacao = curso.getUltimaAtualizacao(); 
        dto.alunosMatriculados = curso.getAlunosMatriculados(); 
        dto.nivel = curso.getNivel();
        dto.modulos = curso.getModulos()
            .stream()
            .map(ModuloCursoDTO::fromEntity)
            .toList();
        return dto;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }           // adicionado
    public String getInstrutor() { return instrutor; }
    public String getDuracao() { return duracao; }
    public int getNumeroAulas() { return numeroAulas; }
    public Date getUltimaAtualizacao() { return ultimaAtualizacao; } // adicionado
    public int getAlunosMatriculados() { return alunosMatriculados; } // adicionado
    public String getNivel() { return nivel; }
    public List<ModuloCursoDTO> getModulos() { return modulos; }
}