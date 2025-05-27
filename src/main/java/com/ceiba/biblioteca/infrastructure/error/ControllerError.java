package com.ceiba.biblioteca.infrastructure.error;

import com.ceiba.biblioteca.domain.exception.PrestamoGuestUserException;
import com.ceiba.biblioteca.domain.exception.PrestamoUserTypeNotAllowedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerError extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PrestamoGuestUserException.class)
    public ResponseEntity<Object> handleGuestUserPrestamoException(PrestamoGuestUserException prestamoGuestUserException) {

        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", prestamoGuestUserException.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(PrestamoUserTypeNotAllowedException.class)
    public ResponseEntity<Object> handleUserTypeExceptionNotAllowed(PrestamoUserTypeNotAllowedException prestamoUserTypeNotAllowedException) {

        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", prestamoUserTypeNotAllowedException.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

}