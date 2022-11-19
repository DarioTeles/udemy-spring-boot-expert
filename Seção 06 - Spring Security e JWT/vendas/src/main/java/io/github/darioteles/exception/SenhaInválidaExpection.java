package io.github.darioteles.exception;

public class SenhaInválidaExpection extends RuntimeException {
    public SenhaInválidaExpection() {
        super("Senha inválida");
    }
}
