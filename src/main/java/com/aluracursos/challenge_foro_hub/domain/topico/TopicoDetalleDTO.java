package com.aluracursos.challenge_foro_hub.domain.topico;

import java.time.LocalDateTime;

public record TopicoDetalleDTO(
    Long id,
    String titulo,
    String mensaje,
    LocalDateTime ultimaActualizacion,
    Estado estado,
    String usuario,
    String curso
) {

    public TopicoDetalleDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getUltimaActualizacion(), topico.getEstado(), topico.getUsuario().getNombre(), topico.getCurso());
    }
}
