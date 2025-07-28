package com.aluracursos.challenge_foro_hub.domain.respuesta;

import java.time.LocalDateTime;

import com.aluracursos.challenge_foro_hub.domain.topico.Topico;
import com.aluracursos.challenge_foro_hub.domain.usuario.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private String mensaje;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column(name = "ultima_modificacion")
    private LocalDateTime ultimaModificacion;
    private boolean solucion;
    private boolean eliminado;

    public Respuesta(RespuestaRegistroDTO datos, Topico topico,Usuario usuario) {
        this.id = null;
        this.topico = topico;
        this.usuario = usuario;
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.ultimaModificacion = this.fechaCreacion;
        this.solucion = false;
        this.eliminado = false;
    }

    public void actualizarInformacion(RespuestaActualizacionDTO datos) {
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }

        if (datos.solucion()) {
            this.solucion = true;
        }

        this.ultimaModificacion = LocalDateTime.now();
    }

    public void eliminar() {
        this.eliminado = true;
    }

}
