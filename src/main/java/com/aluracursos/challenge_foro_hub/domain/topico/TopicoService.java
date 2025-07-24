package com.aluracursos.challenge_foro_hub.domain.topico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aluracursos.challenge_foro_hub.domain.usuario.Usuario;
import com.aluracursos.challenge_foro_hub.domain.usuario.UsuarioRepository;

@Service
public class TopicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public DatosDetalleTopico nuevoTopico(DatosRegistroTopico datos) {
        Usuario usuario = usuarioRepository.findById(datos.usuarioId()).get();
        Topico topico = new Topico(datos, usuario);
        topicoRepository.save(topico);
        return new DatosDetalleTopico(topico);
    }

}
