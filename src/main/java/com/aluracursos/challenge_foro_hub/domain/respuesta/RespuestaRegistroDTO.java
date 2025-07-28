package com.aluracursos.challenge_foro_hub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RespuestaRegistroDTO(
    @NotBlank String mensaje,
    @NotNull Long topicoId,
    @NotNull Long usuarioId
) {}
