package com.aluracursos.challenge_foro_hub.domain.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
    Long id,
    String titulo,
    String mensaje,
    LocalDateTime ultimaActualizacion,
    Estado estado
) {

    public DatosDetalleTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getUltimaActualizacion(), topico.getEstado());
    }
}
