package com.aluracursos.challenge_foro_hub.domain.topico;

public record TopicoActualizacionDTO(
    String titulo,
    String mensaje,
    Estado estado
) {}
