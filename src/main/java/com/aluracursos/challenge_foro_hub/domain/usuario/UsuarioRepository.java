package com.aluracursos.challenge_foro_hub.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Usuario findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.activo = true ORDER BY u.nombre")
    Page<Usuario> listarUsuarios(Pageable paginacion);

}
