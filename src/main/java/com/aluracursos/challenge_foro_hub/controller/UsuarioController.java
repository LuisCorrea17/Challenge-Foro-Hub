package com.aluracursos.challenge_foro_hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.aluracursos.challenge_foro_hub.domain.usuario.UsuarioDetalleDTO;
import com.aluracursos.challenge_foro_hub.domain.usuario.UsuarioRegistroDTO;
import com.aluracursos.challenge_foro_hub.domain.topico.TopicoDetalleDTO;
import com.aluracursos.challenge_foro_hub.domain.usuario.Usuario;
import com.aluracursos.challenge_foro_hub.domain.usuario.UsuarioRepository;
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
        var usuario = new Usuario(datos);
        repository.save(usuario);
        var uri = uriComponentsBuilder.path("/usuarios/{username}").buildAndExpand(usuario.getEmail()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDetalleDTO(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDetalleDTO>> listarUsuarios(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
        var page = usuarioService.listarUsuarios(paginacion);
        return ResponseEntity.ok(page);
    }
}
