package io.github.darioteles.model;

/**
 * Define um cliente.
 */
public class Client {

    private String name;

    /**
     * Altera o nome de um cliente.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna o nome de um cliente.
     * @return name
     */
    public String getName() {
        return name;
    }

}
