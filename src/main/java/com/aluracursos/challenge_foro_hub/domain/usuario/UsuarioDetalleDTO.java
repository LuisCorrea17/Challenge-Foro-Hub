package com.aluracursos.challenge_foro_hub.domain.usuario;

import java.time.LocalDateTime;

public record UsuarioDetalleDTO(
    Long id,
    String nombre,
    String email,
    LocalDateTime fechaRegistro
) 
{
    public UsuarioDetalleDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getFechaRegistro());
    }
}
