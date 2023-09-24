package com.desafio.seasolution.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
@ControllerAdvice

public class CustomGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("status", BAD_REQUEST.value());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        body.put("errors", errors);

        return new ResponseEntity<>(body, BAD_REQUEST);
    }

    @ExceptionHandler(SetorExistenteException.class)
    public ResponseEntity<Object> handleSetorNaoEncontrado(SetorExistenteException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", BAD_REQUEST.value());
        body.put("Setor com este nome já existe", ex.getMessage());
        return new ResponseEntity<>(body, BAD_REQUEST);
    }

}
