package com.aluracursos.challenge_foro_hub.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record CursoRegistroDTO(
    @NotBlank String nombre,
    @NotBlank String descripcion,
    @NotBlank String categoria
) {}
