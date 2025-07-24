package com.aluracursos.challenge_foro_hub.domain.usuario;

import java.time.LocalDateTime;

public record DatosDetalleUsuario(
    Long id,
    String nombre,
    String email,
    LocalDateTime fechaRegistro
) 
{
    public DatosDetalleUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getFechaRegistro());
    }
}
