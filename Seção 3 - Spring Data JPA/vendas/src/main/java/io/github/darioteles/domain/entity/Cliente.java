package io.github.darioteles.domain.entity;

import javax.persistence.*;

/**
 * Define um cliente.
 */
@Entity
@Table
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column(length = 100)
    private String nome;

    /**
     * Constrói um cliente sem parêmtros
     */
    public Cliente() {
    }

    /**
     * Constrói um cliente.
     * @param id
     * @param nome
     */
    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    /**
     * Constrói um cliente.
     * @param nome
     */
    public Cliente(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o Id do cliente.
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Atualiza o id do cliente.
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o nome do cliente.
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Atualiza o nome do cliente.
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna informações do cliente.
     * @return
     */
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
