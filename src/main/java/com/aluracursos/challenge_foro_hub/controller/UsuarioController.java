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

import com.aluracursos.challenge_foro_hub.domain.usuario.UsuarioActualizacionDTO;
import com.aluracursos.challenge_foro_hub.domain.usuario.UsuarioDetalleDTO;
import com.aluracursos.challenge_foro_hub.domain.usuario.UsuarioRegistroDTO;
import com.aluracursos.challenge_foro_hub.domain.usuario.UsuarioService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDetalleDTO> registrarNuevoUsuario(@RequestBody @Valid UsuarioRegistroDTO datos, UriComponentsBuilder uriComponentsBuilder) {
        var usuario = usuarioService.nuevoUsuario(datos);
        var uri = uriComponentsBuilder.path("/usuarios/{username}").buildAndExpand(usuario.email()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDetalleDTO>> listarUsuarios(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
        var page = usuarioService.listarUsuarios(paginacion);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDetalleDTO> detallarUsuario(@PathVariable Long id) {
        var usuario = usuarioService.detallarUsuario(id);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioDetalleDTO> actualizarUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioActualizacionDTO datos) {
        var usuario = usuarioService.actualizarUsuario(id, datos);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
