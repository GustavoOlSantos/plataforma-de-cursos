package com.plataforma.cursos.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.http.*;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;
import com.plataforma.cursos.domain.entities.Cursos;
import com.plataforma.cursos.DTO.CursosDTO;
import com.plataforma.cursos.DTO.ViewCursosDTO;
import com.plataforma.cursos.DTO.SubcategoriaDTO;
import com.plataforma.cursos.domain.entities.ModuloCurso;
import com.plataforma.cursos.domain.entities.Subcategoria;
import com.plataforma.cursos.domain.entities.AulaCurso;
import com.plataforma.cursos.DTO.CriarCursoDTO;
import com.plataforma.cursos.DTO.CriarModuloDTO;
import com.plataforma.cursos.DTO.CriarAulaDTO;
import com.plataforma.cursos.repository.SubcategoriaRepository;
import com.plataforma.cursos.utils.NullPropertyUtils;
import com.plataforma.cursos.exception.BusinessException;
import com.plataforma.cursos.repository.CursosRepository;
import java.util.Optional;
import java.util.List;

@Service
public class CursosService {

    private final CursosRepository repository;
    private final SubcategoriaRepository subcategoriaRepository;

    public CursosService(CursosRepository repository, SubcategoriaRepository subcategoriaRepository) {
        this.repository = repository;
        this.subcategoriaRepository = subcategoriaRepository;
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
            throw new BusinessException("Nenhum curso encontrado", true,  HttpStatus.NOT_FOUND);
        }

        return cursos.stream()
        .map(CursosDTO::fromEntity)
        .toList();
    }

    public CursosDTO findById(Long id) {
        Optional<Cursos> curso = repository.findById(id);
        if (curso.isEmpty()) {
            throw new BusinessException("Curso não encontrado", true, HttpStatus.NOT_FOUND);
        }

       return CursosDTO.fromEntity(curso.get());
    }

    public List<CursosDTO> findByName(String name) {

        List<Cursos> cursos = repository.findByNomeContainingIgnoreCase(name);

        if (cursos.isEmpty()) {
            throw new BusinessException("Nenhum curso encontrado", true,  HttpStatus.NOT_FOUND);
        }

        return cursos.stream()
        .map(CursosDTO::fromEntity)
        .toList();
    }

    public List<CursosDTO> findBySlug(String name) {

        List<Cursos> cursos = repository.findBySlugContainingIgnoreCase(name);

        if (cursos.isEmpty()) {
            throw new BusinessException("Nenhum curso encontrado", true,  HttpStatus.NOT_FOUND);
        }

        return cursos.stream()
        .map(CursosDTO::fromEntity)
        .toList();
    }

    public List<ViewCursosDTO> findAulasBySlug(String name) {

        List<Cursos> cursos = repository.findBySlugContainingIgnoreCase(name);

        if (cursos.isEmpty()) {
            throw new BusinessException("Nenhum curso encontrado", true,  HttpStatus.NOT_FOUND);
        }

        return cursos.stream()
        .map(ViewCursosDTO::fromEntity)
        .toList();
    }

    public CursosDTO registerCompleto(CriarCursoDTO dto) {

        Cursos curso = new Cursos();
        curso.setSlug(dto.slug);
        curso.setNome(dto.nome);
        curso.setSubtitulo(dto.subtitulo);
        curso.setDescricao(dto.descricao);
        curso.setInstrutor(dto.instrutor);
        curso.setDuracao(dto.duracao);
        curso.setNumeroAulas(dto.numeroAulas);
        curso.setImagemUrl(dto.imagemUrl);
        curso.setUltimaAtualizacao(dto.ultimaAtualizacao);
        curso.setIdioma(dto.idioma);
        curso.setNivel(dto.nivel);
        curso.setAlunosMatriculados(dto.alunosMatriculados);
        curso.setPreco(dto.preco);
        curso.setRequisitos(dto.requisitos);

        if (dto.subcategorias != null) {
            List<Subcategoria> subcategorias = dto.subcategorias.stream()
                .map(ref -> subcategoriaRepository.findById(ref.id)
                    .orElseThrow(() -> new BusinessException(
                        "Subcategoria informada não encontrada: " + ref.id, true, HttpStatus.BAD_REQUEST)))
                .toList();
            curso.setSubcategorias(subcategorias);
        }

        if (dto.modulos != null) {
            List<ModuloCurso> modulos = dto.modulos.stream().map(moduloDTO -> {
                ModuloCurso modulo = new ModuloCurso();
                modulo.setTitulo(moduloDTO.titulo);
                modulo.setDescricao(moduloDTO.descricao);
                modulo.setOrdem(moduloDTO.ordem);
                modulo.setCurso(curso); // FK

                if (moduloDTO.aulas != null) {
                    List<AulaCurso> aulas = moduloDTO.aulas.stream().map(aulaDTO -> {
                        AulaCurso aula = new AulaCurso();
                        aula.setTitulo(aulaDTO.titulo);
                        aula.setDescricao(aulaDTO.descricao);
                        aula.setVideo_url(aulaDTO.videoUrl);
                        aula.setThumbnail(aulaDTO.thumbnail);
                        aula.setDuracao_segundos(aulaDTO.duracaoSegundos);
                        aula.setOrdem(aulaDTO.ordem);
                        aula.setGratuita(aulaDTO.gratuita);
                        aula.setPublicada(aulaDTO.publicada);
                        aula.setModulo(modulo); // FK
                        return aula;
                    }).toList();
                    modulo.setAulas(aulas);
                }
                return modulo;
            }).toList();
            curso.setModulos(modulos);
        }

        Cursos salvo = repository.save(curso);
        return CursosDTO.fromEntity(salvo);
    }

    public Cursos register(Cursos curso) {
        if(curso.isValido()){
            throw new BusinessException("Dados incompletos para cadastro", true,  HttpStatus.BAD_REQUEST);
        }

        if(!repository.findByNomeContainingIgnoreCase(curso.getNome()).isEmpty()){
            throw new BusinessException("Curso com esse nome já cadastrado no sistema", true, HttpStatus.BAD_REQUEST);
        }

        return repository.save(curso);
    }

    public CursosDTO update(Long id, Cursos dadosAtualizados){
        Optional<Cursos> curso = repository.findById(id);

        if (curso.isEmpty()) {
            throw new BusinessException("Curso não encontrado", true, HttpStatus.NOT_FOUND);
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
            throw new BusinessException("Curso não encontrado", true, HttpStatus.NOT_FOUND);
        }

        repository.deleteById(id);
    }
}