package com.aluracursos.challenge_foro_hub.domain.respuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aluracursos.challenge_foro_hub.domain.topico.Topico;
import com.aluracursos.challenge_foro_hub.domain.topico.TopicoRepository;
import com.aluracursos.challenge_foro_hub.domain.usuario.Usuario;
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
        Usuario usuario = usuarioRepository.findById(datos.usuarioId()).get();
        Topico topico = topicoRepository.findById(datos.topicoId()).get();
        Respuesta respuesta = new Respuesta(datos, topico, usuario);
        respuestaRepository.save(respuesta);
        return new RespuestaDetalleDTO(respuesta);
    }

    public Page<RespuestaDetalleDTO> listarRespuestas(Pageable paginacion) {
        var page = respuestaRepository.listarRespuestas(paginacion);
        return page.map(RespuestaDetalleDTO::new);
    }

    public RespuestaDetalleDTO actualizarRespuesta(Long id, RespuestaActualizacionDTO datos) {
        var respuesta = respuestaRepository.getReferenceById(id);
        respuesta.actualizarInformacion(datos);

        if (datos.solucion()) {
            var topico = topicoRepository.getReferenceById(respuesta.getTopico().getId());
            topico.marcarComoResuelto();
        }

        return new RespuestaDetalleDTO(respuesta);
    }

	public void eliminarRespuesta(Long id) {
		var respuesta = respuestaRepository.getReferenceById(id);
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

    public RespuestaDetalleDTO detallarTopico(Long id) {
        var respuesta = respuestaRepository.getReferenceById(id);
        return new RespuestaDetalleDTO(respuesta); 
    }



}
