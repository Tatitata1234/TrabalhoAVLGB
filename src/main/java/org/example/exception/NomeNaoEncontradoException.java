package org.example.exception;

public class NomeNaoEncontradoException extends RuntimeException {
    public NomeNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
