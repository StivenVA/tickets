package com.tickets.infrastructure.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFound(TicketNotFoundException e, HttpServletRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                request.getRequestURI(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGeneral(Exception e, HttpServletRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                request.getRequestURI(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
