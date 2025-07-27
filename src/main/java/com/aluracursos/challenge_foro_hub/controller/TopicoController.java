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

import com.aluracursos.challenge_foro_hub.domain.topico.TopicoActualizacionDTO;
import com.aluracursos.challenge_foro_hub.domain.topico.TopicoDetalleDTO;
import com.aluracursos.challenge_foro_hub.domain.topico.TopicoRegistroDTO;
import com.aluracursos.challenge_foro_hub.domain.topico.TopicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDetalleDTO> registrarNuevoTopico(@RequestBody @Valid TopicoRegistroDTO datos, UriComponentsBuilder uriComponentsBuilder) {
        var datosDetalleTopico = topicoService.nuevoTopico(datos);
        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(datosDetalleTopico.id()).toUri();
        return ResponseEntity.created(uri).body(datosDetalleTopico);
    }

    @GetMapping
    public ResponseEntity<Page<TopicoDetalleDTO>> listarTopicos(@PageableDefault(size = 10, sort = {"ultimaActualizacion"}) Pageable paginacion) {
        var page = topicoService.listarTopicos(paginacion);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDetalleDTO> actualizarTopico(@PathVariable Long id, @RequestBody @Valid TopicoActualizacionDTO datos) {
        var topico = topicoService.actualizarTopico(id, datos);
        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<TopicoDetalleDTO> detallarTopico(@PathVariable Long id) {
        var topico = topicoService.detallarTopico(id);
        return ResponseEntity.ok(topico);
    }

}
