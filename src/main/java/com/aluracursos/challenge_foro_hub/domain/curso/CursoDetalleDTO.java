package com.aluracursos.challenge_foro_hub.domain.curso;

public record CursoDetalleDTO(
    Long id,
    String nombre,
    String descripcion,
    String categoria
) 
{
    public CursoDetalleDTO(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getDescripcion(), curso.getCategoria());
    }
}
