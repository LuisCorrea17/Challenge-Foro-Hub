package com.aluracursos.challenge_foro_hub.domain.topico;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aluracursos.challenge_foro_hub.domain.ValidacionException;
import com.aluracursos.challenge_foro_hub.domain.curso.CursoRepository;
import com.aluracursos.challenge_foro_hub.domain.usuario.UsuarioRepository;

@Service
public class TopicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public TopicoDetalleDTO nuevoTopico(TopicoRegistroDTO datos) {
        var usuario = usuarioRepository.findById(datos.usuarioId());
        if (usuario.isEmpty() || !usuario.get().getActivo()) {
            throw new NoSuchElementException("Usuario no encontrado o inactivo");
        }
        var curso = cursoRepository.findById(datos.cursoId());
        if (curso.isEmpty()) {
            throw new NoSuchElementException("Curso no encontrado");
        }
        Topico topico = new Topico(datos, usuario.get(), curso.get());
        topicoRepository.save(topico);
        return new TopicoDetalleDTO(topico);
    }

    public Page<TopicoDetalleDTO> listarTopicos(Pageable paginacion) {
        var page = topicoRepository.listarTopicos(paginacion);
        return page.map(TopicoDetalleDTO::new);
    }

    public TopicoDetalleDTO actualizarTopico(Long id, TopicoActualizacionDTO datos) {
        var topico = topicoRepository.getReferenceById(id);
        validarTopico(topico);
        topico.actualizarInformacion(datos);
        return new TopicoDetalleDTO(topico);
    }

    public void eliminarTopico(Long id) {
        var topico = topicoRepository.getReferenceById(id);
        validarTopico(topico);
        topico.eliminar();
    }

    public TopicoDetalleDTO detallarTopico(Long id) {
        var topico = topicoRepository.getReferenceById(id);
        validarTopico(topico);
        return new TopicoDetalleDTO(topico);
    }

    public void validarTopico(Topico topico) {
        if (topico.getEstado().equals(Estado.DELETED)) {
            throw new ValidacionException("El topico se encuentra eliminado del sistema");
        }
    }

}
