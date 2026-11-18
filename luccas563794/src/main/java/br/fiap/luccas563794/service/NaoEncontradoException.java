package br.fiap.luccas563794.service;

public class NaoEncontradoException extends RuntimeException {
    public NaoEncontradoException() {
        super("Registro não encontrado.");
    }

    public NaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
