package com.aluracursos.challenge_foro_hub.infra.exeptions;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aluracursos.challenge_foro_hub.domain.ValidacionException;

import jakarta.persistence.EntityNotFoundException;


@RestControllerAdvice
public class GestorDeErrores {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> gestionarErrorDuplicacion(DataIntegrityViolationException ex) {
        String mensaje = "Ya existe un tópico con el mismo título y mensaje";
        var error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), mensaje, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> gestionarErrorIdNoEncontrado(EntityNotFoundException ex) {
        var error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Topico con el id no encontrado", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // @ExceptionHandler(UsernameNotFoundException.class)
    // public ResponseEntity<ErrorResponse> gestionarErrorUsuarioNoEncontrado(UsernameNotFoundException ex) {
    //     var error = new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Usuario no encontrado o inactivo", LocalDateTime.now());
    //     return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    // }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DatosErrorValidacion>> gestionarError400(MethodArgumentNotValidException ex) {
        var errores = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errores.stream()
            .map(DatosErrorValidacion::new)
            .toList());
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity<String> gestionarErrorDeValidacion(ValidacionException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

     public record DatosErrorValidacion(
        String campo, 
        String mensaje
    ) 
    {
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    public record ErrorResponse(int Status, String mensaje, LocalDateTime timestamp) {}
}
