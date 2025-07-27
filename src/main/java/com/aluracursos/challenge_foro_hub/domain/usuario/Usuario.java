package com.aluracursos.challenge_foro_hub.domain.usuario;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
    private Boolean activo;

    public Usuario(UsuarioRegistroDTO datos) {
        this.id =  null;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.fechaRegistro = LocalDateTime.now();
        this.activo = true;
    }
    
}
