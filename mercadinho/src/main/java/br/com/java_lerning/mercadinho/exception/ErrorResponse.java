package br.com.java_lerning.mercadinho.exception;

import java.time.LocalDateTime;

public record ErrorResponse (
        int status,
        String message,
        LocalDateTime timestamp
){}
