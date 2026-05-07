package com.plataforma.cursos.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;
import com.plataforma.cursos.domain.Cursos;
import com.plataforma.cursos.DTO.CursosDTO;
import com.plataforma.cursos.DTO.SubcategoriaDTO;
import com.plataforma.cursos.utils.NullPropertyUtils;
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

    public List<CursosDTO> findAll() {
        List<Cursos> cursos = repository.findAll();

        return cursos.stream()
            .map(CursosDTO::fromEntity)
            .toList();
    }

    public List<CursosDTO> findBestSellers(){
        List<Cursos> cursos = repository.findTop10ByOrderByAlunosMatriculadosDesc();
        
        if (cursos.isEmpty()) {
            throw new BusinessException("Nenhum curso encontrado", true);
        }

        return cursos.stream()
        .map(CursosDTO::fromEntity)
        .toList();
    }

    public CursosDTO findById(Long id) {
        Optional<Cursos> curso = repository.findById(id);
        if (curso.isEmpty()) {
            throw new BusinessException("Curso não encontrado", true);
        }

       return CursosDTO.fromEntity(curso.get());
    }

    public List<CursosDTO> findByName(String name) {

        List<Cursos> cursos = repository.findByNomeContainingIgnoreCase(name);

        if (cursos.isEmpty()) {
            throw new BusinessException("Nenhum curso encontrado", true);
        }

        return cursos.stream()
        .map(CursosDTO::fromEntity)
        .toList();
    }

    public List<CursosDTO> findBySlug(String name) {

        List<Cursos> cursos = repository.findBySlugContainingIgnoreCase(name);

        if (cursos.isEmpty()) {
            throw new BusinessException("Nenhum curso encontrado", true);
        }

        return cursos.stream()
        .map(CursosDTO::fromEntity)
        .toList();
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

    public CursosDTO update(Long id, Cursos dadosAtualizados){
        Optional<Cursos> curso = repository.findById(id);

        if (curso.isEmpty()) {
            throw new BusinessException("Curso não encontrado", true);
        }

        String[] ignoredProperties = Stream.concat(
            Stream.of("id"),
            Arrays.stream(
                    NullPropertyUtils.getNullPropertyNames(dadosAtualizados)
            )
        ).toArray(String[]::new);

        BeanUtils.copyProperties(dadosAtualizados, curso.get(), ignoredProperties);

        Cursos salvo = repository.save(curso.get());
        return CursosDTO.fromEntity(salvo);
    }

    public void deleteById(Long id){
        Optional<Cursos> curso = repository.findById(id);
        if (curso.isEmpty()) {
            throw new BusinessException("Curso não encontrado", true);
        }

        repository.deleteById(id);
    }
}