package com.aluracursos.challenge_foro_hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.aluracursos.challenge_foro_hub.domain.curso.Curso;
import com.aluracursos.challenge_foro_hub.domain.curso.CursoDetalleDTO;
import com.aluracursos.challenge_foro_hub.domain.curso.CursoRegistroDTO;
import com.aluracursos.challenge_foro_hub.domain.curso.CursoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<CursoDetalleDTO> registrarNuevoCurso(@RequestBody @Valid CursoRegistroDTO datos, UriComponentsBuilder uriComponentsBuilder) {
        var curso = new Curso(datos);
        cursoRepository.save(curso);
        var uri = uriComponentsBuilder.path("/cursos/{nombreCurso}").buildAndExpand(curso.getNombre()).toUri();
        return ResponseEntity.created(uri).body(new CursoDetalleDTO(curso));
    }
}
