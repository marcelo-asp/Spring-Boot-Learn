package br.com.java_lerning.mercadinho.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ProdutoNotFoundException.class)
    public ResponseEntity<?> handleProdutoNotFound(ProdutoNotFoundException exception){
        ErrorResponse error = new ErrorResponse(
                404,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    public ResponseEntity<?> handleProdutoCreation(ProdutoCreationException exception){
        ErrorResponse error = new ErrorResponse(
                400,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    public ResponseEntity<?> handleGenericException(Exception exception){
        ErrorResponse error = new ErrorResponse(
                500,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
