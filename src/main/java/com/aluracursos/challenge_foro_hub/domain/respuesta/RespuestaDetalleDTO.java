package com.aluracursos.challenge_foro_hub.domain.respuesta;

import java.time.LocalDateTime;

public record RespuestaDetalleDTO(
    Long id,
    String mensaje,
    String topico,
    String usuario,
    LocalDateTime ultimaModificacion
) {
    public RespuestaDetalleDTO(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico().getTitulo(), respuesta.getUsuario().getNombre(), respuesta.getUltimaModificacion());
    }
}
