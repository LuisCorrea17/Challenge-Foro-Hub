package com.aluracursos.challenge_foro_hub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopico(
    @NotNull Long id,
    String titulo,
    String mensaje,
    Estado estado
) {}
