package com.tickets.infrastructure.controller.exception;

import java.time.LocalDateTime;

public record ExceptionResponse(
    String message,
    String path,
    int status,
    LocalDateTime timestamp
) {}
