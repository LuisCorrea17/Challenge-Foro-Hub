package com.aluracursos.challenge_foro_hub.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aluracursos.challenge_foro_hub.domain.ValidacionException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioDetalleDTO nuevoUsuario(UsuarioRegistroDTO datos) {
        var contrasenaEncriptada = passwordEncoder.encode(datos.contrasena());
        var usuario = new Usuario(datos, contrasenaEncriptada);
        usuarioRepository.save(usuario);
        return new UsuarioDetalleDTO(usuario);
    }    
    
    public Page<UsuarioDetalleDTO> listarUsuarios(Pageable paginacion) {
        var page = usuarioRepository.listarUsuarios(paginacion);
        return page.map(UsuarioDetalleDTO::new);
    }

    public UsuarioDetalleDTO detallarUsuario(Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        validarUsuario(usuario);
        return new UsuarioDetalleDTO(usuario);
    }

    public UsuarioDetalleDTO actualizarUsuario(Long id, UsuarioActualizacionDTO datos) {
        var usuario = usuarioRepository.getReferenceById(id);
        validarUsuario(usuario);
        if (datos.contrasena() != null) {
            var contrasenaEncriptada = passwordEncoder.encode(datos.contrasena());
            usuario.actualizarInformacion(datos, contrasenaEncriptada);
        } else {
            usuario.actualizarInformacion(datos);
        }
        return new UsuarioDetalleDTO(usuario);
    }

    public void eliminarUsuario(Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        validarUsuario(usuario);
        usuario.eliminar();
    }

    public void validarUsuario(Usuario usuario) {
        if (!usuario.getActivo()) {
            throw new ValidacionException("Usuario inactivo dentro del sistema");
        }
    }
}
