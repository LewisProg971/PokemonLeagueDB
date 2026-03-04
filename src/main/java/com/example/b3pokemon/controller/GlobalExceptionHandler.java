package com.example.b3pokemon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // On intercepte les RuntimeException (celles qu'on a levées dans nos Services)
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("error", ex.getMessage());

    // Si le message contient "introuvable", on renvoie une 404
    if (ex.getMessage().toLowerCase().contains("introuvable")) {
      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Sinon, on renvoie une 400 Bad Request (utile pour les règles métier plus tard)
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}