package com.aluracursos.challenge_foro_hub.domain.topico;

import java.time.LocalDateTime;

import com.aluracursos.challenge_foro_hub.domain.usuario.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private String curso;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Column(name = "ultima_modificacion")
    private LocalDateTime ultimaActualizacion;
    // private List<Respuesta> respuestas;

    public Topico(DatosRegistroTopico datos, Usuario usuario) {
        this.id = null;
        this.titulo = datos.titulo();
        this.usuario = usuario;
        this.mensaje = datos.mensaje();
        this.curso = datos.curso();
        this.fechaCreacion = LocalDateTime.now();
        this.estado = Estado.OPEN;
        this.ultimaActualizacion = this.fechaCreacion;
    }

    public void actualizarInformacion(DatosActualizacionTopico datos) {
        
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }

        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }

        if (datos.estado() != null) {
            this.estado = datos.estado();
        }

        this.ultimaActualizacion = LocalDateTime.now();

    }

}
