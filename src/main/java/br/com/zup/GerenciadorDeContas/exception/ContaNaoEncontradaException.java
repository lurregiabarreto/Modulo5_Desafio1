package br.com.zup.GerenciadorDeContas.exception;

public class ContaNaoEncontradaException extends RuntimeException{

    public ContaNaoEncontradaException (String message) {
        super(message);
    }
}
