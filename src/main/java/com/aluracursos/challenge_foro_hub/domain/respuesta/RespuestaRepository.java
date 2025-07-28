package com.aluracursos.challenge_foro_hub.domain.respuesta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long>{

    @Query("SELECT r FROM Respuesta r WHERE r.eliminado != true ORDER BY r.ultimaModificacion DESC")
    Page<Respuesta> listarRespuestas(Pageable paginacion);

    @Query("SELECT r FROM Respuesta r WHERE r.topico.id = :topicoId")
    Page<Respuesta> listarRespuestasDeUnTopico(Pageable paginacion, Long topicoId);

    @Query("SELECT r FROM Respuesta r WHERE r.usuario.id = :usuarioId")
    Page<Respuesta> listarRespuestasDeUnUsuario(Pageable paginacion, Long usuarioId);

}
