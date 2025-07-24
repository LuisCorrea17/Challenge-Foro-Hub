package com.aluracursos.challenge_foro_hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.aluracursos.challenge_foro_hub.domain.usuario.DatosDetalleUsuario;
import com.aluracursos.challenge_foro_hub.domain.usuario.DatosRegistroUsuario;
import com.aluracursos.challenge_foro_hub.domain.usuario.Usuario;
import com.aluracursos.challenge_foro_hub.domain.usuario.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    public ResponseEntity<DatosDetalleUsuario> registrarNuevoUsuario(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriComponentsBuilder) {
        var usuario = new Usuario(datos);
        repository.save(usuario);
        var uri = uriComponentsBuilder.path("/usuarios/{username}").buildAndExpand(usuario.getEmail()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleUsuario(usuario));
    }
}
