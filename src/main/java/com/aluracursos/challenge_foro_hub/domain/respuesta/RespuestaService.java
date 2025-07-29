package com.aluracursos.challenge_foro_hub.domain.respuesta;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aluracursos.challenge_foro_hub.domain.ValidacionException;
import com.aluracursos.challenge_foro_hub.domain.topico.Estado;
import com.aluracursos.challenge_foro_hub.domain.topico.TopicoRepository;
import com.aluracursos.challenge_foro_hub.domain.usuario.UsuarioRepository;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public RespuestaDetalleDTO nuevaRespuesta(RespuestaRegistroDTO datos) {
        var topico = topicoRepository.findById(datos.topicoId());
        if (topico.isEmpty() || topico.get().getEstado() == Estado.DELETED) {
            throw new NoSuchElementException("Topico no encontrado o eliminado");
        }
        var usuario = usuarioRepository.findById(datos.usuarioId());
        if (usuario.isEmpty() || !usuario.get().getActivo()) {
            throw new NoSuchElementException("Usuario no encontrado o inactivo");
        }
        Respuesta respuesta = new Respuesta(datos, topico.get(), usuario.get());
        respuestaRepository.save(respuesta);
        return new RespuestaDetalleDTO(respuesta);
    }

    public Page<RespuestaDetalleDTO> listarRespuestas(Pageable paginacion) {
        var page = respuestaRepository.listarRespuestas(paginacion);
        return page.map(RespuestaDetalleDTO::new);
    }

    public RespuestaDetalleDTO actualizarRespuesta(Long id, RespuestaActualizacionDTO datos) {
        var respuesta = respuestaRepository.getReferenceById(id);
        validarRespuesta(respuesta);
        respuesta.actualizarInformacion(datos);

        if (datos.solucion()) {
            var topico = topicoRepository.getReferenceById(respuesta.getTopico().getId());
            topico.marcarComoResuelto();
        }

        return new RespuestaDetalleDTO(respuesta);
    }

	public void eliminarRespuesta(Long id) {
		var respuesta = respuestaRepository.getReferenceById(id);
        validarRespuesta(respuesta);
        respuesta.eliminar();
	}

    public Page<RespuestaDetalleDTO> listarRespuestasDeUnTopico(Pageable paginacion, Long topicoId) {
        var respuestas = respuestaRepository.listarRespuestasDeUnTopico(paginacion, topicoId);
        return respuestas.map(RespuestaDetalleDTO::new);
    }

    public Page<RespuestaDetalleDTO> listarRespuestasDeUnUsuario(Pageable paginacion, Long usuarioId) {
        var respuestas = respuestaRepository.listarRespuestasDeUnUsuario(paginacion, usuarioId);
        return respuestas.map(RespuestaDetalleDTO::new);
    }

    public RespuestaDetalleDTO detallarRespuesta(Long id) {
        var respuesta = respuestaRepository.getReferenceById(id);
        validarRespuesta(respuesta);
        return new RespuestaDetalleDTO(respuesta); 
    }

    public void validarRespuesta(Respuesta respuesta) {
        if (respuesta.isEliminado()) {
            throw new ValidacionException("La respuesta se encuentra eliminada del sistema");
        }
    }

}
