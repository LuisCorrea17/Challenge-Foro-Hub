package com.aluracursos.challenge_foro_hub.domain.topico;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<DatosDetalleTopico> listarTopicos() {
        var topicos = topicoRepository.listarTopicos();
        return topicos.stream()
            .map(t -> new DatosDetalleTopico(t))
            .collect(Collectors.toList());
    }

    public DatosDetalleTopico actualizarTopico(DatosActualizacionTopico datos) {
        var topico = topicoRepository.getReferenceById(datos.id());
        topico.actualizarInformacion(datos);
        return new DatosDetalleTopico(topico);
    }

    public void eliminarTopico(Long id) {
        var topico = topicoRepository.getReferenceById(id);
        topico.eliminar();
    }

}
