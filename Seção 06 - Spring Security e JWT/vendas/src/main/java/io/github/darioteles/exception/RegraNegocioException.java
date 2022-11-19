package io.github.darioteles.exception;

/**
 * Define um exceção genérica que possui um parâmetro message em seu construtor.
 */
public class RegraNegocioException extends RuntimeException{

    public RegraNegocioException(String message) {
        super(message);
    }

}
