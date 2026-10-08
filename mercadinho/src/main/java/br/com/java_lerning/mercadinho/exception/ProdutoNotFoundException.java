package br.com.java_lerning.mercadinho.exception;

public class ProdutoNotFoundException extends RuntimeException{

    public ProdutoNotFoundException(String message){
        super(message);
    }
}
