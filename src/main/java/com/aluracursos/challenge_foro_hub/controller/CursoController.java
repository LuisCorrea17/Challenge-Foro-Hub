package com.aluracursos.challenge_foro_hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.aluracursos.challenge_foro_hub.domain.curso.CursoActualizacionDTO;
import com.aluracursos.challenge_foro_hub.domain.curso.CursoDetalleDTO;
import com.aluracursos.challenge_foro_hub.domain.curso.CursoRegistroDTO;
import com.aluracursos.challenge_foro_hub.domain.curso.CursoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    @Transactional
    public ResponseEntity<CursoDetalleDTO> registrarNuevoCurso(@RequestBody @Valid CursoRegistroDTO datos, UriComponentsBuilder uriComponentsBuilder) {
        var curso = cursoService.nuevoCurso(datos);
        var uri = uriComponentsBuilder.path("/cursos/{nombreCurso}").buildAndExpand(curso.nombre()).toUri();
        return ResponseEntity.created(uri).body(curso);
    }

    @GetMapping
    public ResponseEntity<Page<CursoDetalleDTO>> listarCurso(@PageableDefault(size = 10, sort = {"ultimaModificacion"}) Pageable paginacion) {
        var page = cursoService.listarCursos(paginacion);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDetalleDTO> detallarCurso(@PathVariable Long id) {
        var curso = cursoService.detallarCurso(id);
        return ResponseEntity.ok(curso);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CursoDetalleDTO> actualizarCurso(@PathVariable Long id, @RequestBody @Valid CursoActualizacionDTO datos) {
        var curso = cursoService.actualizarCurso(id, datos);
        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}
