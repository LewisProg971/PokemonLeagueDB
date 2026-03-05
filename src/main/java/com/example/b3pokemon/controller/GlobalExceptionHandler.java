package com.example.b3pokemon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("error", ex.getMessage());

    if (ex.getMessage().toLowerCase().contains("introuvable")) {
      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}