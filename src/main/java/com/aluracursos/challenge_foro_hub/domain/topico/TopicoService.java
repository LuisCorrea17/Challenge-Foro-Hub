package com.aluracursos.challenge_foro_hub.domain.topico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aluracursos.challenge_foro_hub.domain.curso.Curso;
import com.aluracursos.challenge_foro_hub.domain.curso.CursoRepository;
import com.aluracursos.challenge_foro_hub.domain.usuario.Usuario;
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
        Usuario usuario = usuarioRepository.findById(datos.usuarioId()).get();
        Curso curso = cursoRepository.findById(datos.cursoId()).get();
        Topico topico = new Topico(datos, usuario, curso);
        topicoRepository.save(topico);
        return new TopicoDetalleDTO(topico);
    }

    public Page<TopicoDetalleDTO> listarTopicos(Pageable paginacion) {
        var page = topicoRepository.listarTopicos(paginacion);
        return page.map(TopicoDetalleDTO::new);
    }

    public TopicoDetalleDTO actualizarTopico(Long id, TopicoActualizacionDTO datos) {
        var topico = topicoRepository.getReferenceById(id);
        topico.actualizarInformacion(datos);
        return new TopicoDetalleDTO(topico);
    }

    public void eliminarTopico(Long id) {
        var topico = topicoRepository.getReferenceById(id);
        topico.eliminar();
    }

    public TopicoDetalleDTO detallarTopico(Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return new TopicoDetalleDTO(topico);
    }

    //TODO: Validar que no se pueda eliminar un topico que no exista ni que que ya se haya eliminado
    //TODO: Validar que no se pueda hacer get ni actualizar un topico ya eliminado
    //TODO: Agregar log de cuando no existe un usuarios o curso al crear un topico

}
