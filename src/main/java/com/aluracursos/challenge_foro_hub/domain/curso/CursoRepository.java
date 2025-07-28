package com.aluracursos.challenge_foro_hub.domain.curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CursoRepository extends JpaRepository<Curso, Long>{

    @Query("SELECT c FROM Curso c ORDER BY c.nombre")
    Page<Curso> listarCursos(Pageable paginacion);

}
