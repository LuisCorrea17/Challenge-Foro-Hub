package com.aluracursos.challenge_foro_hub.domain.topico;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

    @Query("SELECT t FROM Topico t WHERE t.estado != 'DELETED'")
    List<Topico> listarTopicos();

}
