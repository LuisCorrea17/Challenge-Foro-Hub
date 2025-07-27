package com.aluracursos.challenge_foro_hub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

    @Query("SELECT t FROM Topico t WHERE t.estado != 'DELETED' ORDER BY t.fechaCreacion")
    Page<Topico> listarTopicos(Pageable paginacion);

}
