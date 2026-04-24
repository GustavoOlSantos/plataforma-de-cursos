package com.plataforma.cursos.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.plataforma.cursos.domain.Cursos;
import com.plataforma.cursos.exception.BusinessException;
import com.plataforma.cursos.repository.CursosRepository;
import java.util.Optional;
import java.util.List;

@Service
public class CursosService {

    private final CursosRepository repository;

    public CursosService(CursosRepository repository) {
        this.repository = repository;
    }

    public List<Cursos> findAll() {
        return repository.findAll();
    }

    public Cursos findById(Long id) {
        Optional<Cursos> curso = repository.findById(id);
        if (curso.isEmpty()) {
            throw new BusinessException("Curso não encontrado", true);
        }
        return curso.get();
    }

    public List<Cursos> findByName(String name) {

        List<Cursos> cursos = repository.findByNomeContainingIgnoreCase(name);

        if (cursos.isEmpty()) {
            throw new BusinessException("Nenhum curso encontrado", true);
        }
        return cursos;
    }

    public Cursos register(Cursos curso) {
        if(curso.isValido()){
            throw new BusinessException("Dados incompletos para cadastro", true);
        }

        if(!repository.findByNomeContainingIgnoreCase(curso.getNome()).isEmpty()){
            throw new BusinessException("Curso com esse nome já cadastrado no sistema", true);
        }

        return repository.save(curso);
    }
}