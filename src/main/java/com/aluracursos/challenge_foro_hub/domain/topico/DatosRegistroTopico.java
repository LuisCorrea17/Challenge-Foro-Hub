package com.aluracursos.challenge_foro_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroTopico(
    @NotBlank String titulo,
    @NotBlank String mensaje,
    @NotBlank String autor,
    @NotBlank String curso
) {}
