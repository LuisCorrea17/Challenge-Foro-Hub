package com.aluracursos.challenge_foro_hub.domain.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public CursoDetalleDTO nuevoCurso(CursoRegistroDTO datos) {
        Curso curso = new Curso(datos);
        cursoRepository.save(curso);
        return new CursoDetalleDTO(curso);
    }

    public Page<CursoDetalleDTO> listarCursos(Pageable paginacion) {
        var page = cursoRepository.listarCursos(paginacion);
        return page.map(CursoDetalleDTO::new);
    }

    public CursoDetalleDTO detallarCurso(Long id) {
        var curso = cursoRepository.getReferenceById(id);
        return new CursoDetalleDTO(curso);
    }

    public CursoDetalleDTO actualizarCurso(Long id, CursoActualizacionDTO datos) {
        var curso = cursoRepository.getReferenceById(id);
        curso.actualizarInformacion(datos);
        return new CursoDetalleDTO(curso);
    }

    public void eliminarCurso(Long id) {
        var curso = cursoRepository.getReferenceById(id);
        cursoRepository.delete(curso);
    }

}
