package com.fsbr.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class RegistrationExceptionHandler {

    private static final String KEY_ERROR = "error";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> resourceNotFound(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(KEY_ERROR, e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> illegalArgument(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(KEY_ERROR, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> illegalArgument(MethodArgumentNotValidException e) {
        String message = "Dado inválido. verifique e tente novamente";
        if (e.getMessage().contains("não deve estar vazio")) {
            message = "Os dados não devem estar vazios. Verifique e tente novamente.";
        }
        if (e.getMessage().contains("não deve estar vazio")) {
            message = "Os dados não devem estar vazios. Verifique e tente novamente.";
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(KEY_ERROR, message));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String, String>> nullPointer(NullPointerException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(KEY_ERROR, e.getMessage()));
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Map<String, String>> numberFormat(NumberFormatException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(KEY_ERROR, "Dado inválido. verifique e tente novamente"));
    }

}
