package com.aluracursos.challenge_foro_hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aluracursos.challenge_foro_hub.domain.usuario.AutenticacionDTO;
import com.aluracursos.challenge_foro_hub.domain.usuario.Usuario;
import com.aluracursos.challenge_foro_hub.infra.security.TokenJWTDTO;
import com.aluracursos.challenge_foro_hub.infra.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity<TokenJWTDTO> iniciarSesion(@RequestBody @Valid AutenticacionDTO datos) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.contrasena());
        var autenticacion = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generarToken((Usuario)autenticacion.getPrincipal());
        return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));
    }

}
