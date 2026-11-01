package com.wellari.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {

    public ProdutoNaoEncontradoException(String menssagem){
        super(menssagem);
    }
}
