package com.aluracursos.challenge_foro_hub.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<UsuarioDetalleDTO> listarUsuarios(Pageable paginacion) {
        var page = usuarioRepository.listarUsuarios(paginacion);
        return page.map(UsuarioDetalleDTO::new);
    }


}
