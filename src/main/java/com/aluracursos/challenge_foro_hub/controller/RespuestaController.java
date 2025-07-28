package com.aluracursos.challenge_foro_hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.aluracursos.challenge_foro_hub.domain.respuesta.RespuestaActualizacionDTO;
import com.aluracursos.challenge_foro_hub.domain.respuesta.RespuestaDetalleDTO;
import com.aluracursos.challenge_foro_hub.domain.respuesta.RespuestaRegistroDTO;
import com.aluracursos.challenge_foro_hub.domain.respuesta.RespuestaService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @PostMapping
    @Transactional
    public ResponseEntity<RespuestaDetalleDTO> registarRespuesta(@RequestBody @Valid RespuestaRegistroDTO datos, UriComponentsBuilder uriComponentsBuilder) {
        var respuesta = respuestaService.nuevaRespuesta(datos);
        var uri = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(uri).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<RespuestaDetalleDTO>> listarRespuesta(@PageableDefault(size = 10, sort = {"ultimaModificacion"}) Pageable paginacion) {
        var page = respuestaService.listarRespuestas(paginacion);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaDetalleDTO> detallarRespuesta(@PathVariable Long id) {
        var respuesta = respuestaService.detallarTopico(id);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/topico/{topicoId}")
    public ResponseEntity<Page<RespuestaDetalleDTO>> listarRespuestasDeUnTopico(@PageableDefault(size = 10, sort = {"ultimaModificacion"}) Pageable paginacion, @PathVariable Long topicoId) {
        var page = respuestaService.listarRespuestasDeUnTopico(paginacion, topicoId);
        return ResponseEntity.ok(page);
    }   

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Page<RespuestaDetalleDTO>> listarRespuestasDeUnUsuario(@PageableDefault(size = 10, sort = {"ultimaModificacion"}) Pageable paginacion, @PathVariable Long usuarioId) {
        var page = respuestaService.listarRespuestasDeUnUsuario(paginacion, usuarioId);
        return ResponseEntity.ok(page);
    }   

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<RespuestaDetalleDTO> actualizarRespuesta(@PathVariable Long id, @RequestBody @Valid RespuestaActualizacionDTO datos) {
        var respuesta = respuestaService.actualizarRespuesta(id, datos);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminarRespuesta(@PathVariable Long id) {
        respuestaService.eliminarRespuesta(id);
        return ResponseEntity.noContent().build();
    }


}
