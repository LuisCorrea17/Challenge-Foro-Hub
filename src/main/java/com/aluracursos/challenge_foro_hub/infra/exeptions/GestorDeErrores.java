package com.aluracursos.challenge_foro_hub.infra.exeptions;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aluracursos.challenge_foro_hub.domain.ValidacionException;


@RestControllerAdvice
public class GestorDeErrores {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> gestionarErrorDuplicacion(DataIntegrityViolationException ex) {
        String mensaje = "Ya existe un tópico con el mismo título y mensaje.";
        return ResponseEntity.badRequest().body(mensaje);
    }

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
}
