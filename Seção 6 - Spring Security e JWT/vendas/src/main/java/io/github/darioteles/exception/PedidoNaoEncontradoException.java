package io.github.darioteles.exception;

/**
 * Define uma exceção para um pedido não encontrado.
 */
public class PedidoNaoEncontradoException extends RuntimeException {

    public PedidoNaoEncontradoException() {
        super("Pedido não encontrado.");
    }

}
