package com.aluracursos.challenge_foro_hub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.aluracursos.challenge_foro_hub.domain.topico.DatosDetalleTopico;
import com.aluracursos.challenge_foro_hub.domain.topico.DatosRegistroTopico;
import com.aluracursos.challenge_foro_hub.domain.topico.TopicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleTopico> registrarNuevoTopico(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder) {
        var datosDetalleTopico = topicoService.nuevoTopico(datos);
        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(datosDetalleTopico.id()).toUri();
        return ResponseEntity.created(uri).body(datosDetalleTopico);
    }

    @GetMapping
    public ResponseEntity<List<DatosDetalleTopico>> listarTopicos() {
        var topicos = topicoService.listarTopicos();
        return ResponseEntity.ok().body(topicos);
    } 

}
