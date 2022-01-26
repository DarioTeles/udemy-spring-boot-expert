package io.github.darioteles.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

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

    @Column(length = 11)
    private String cpf;

    @JsonIgnore //Ele diz para o transformador de objetos em Json que deve ignorar esse parâmetro.
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

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
     * Retorna o CPF do cliente.
     * @return cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Atualiza o CPF docliente.
     * @param cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Retorna a lista de pedidos do cliente.
     * @return
     */
    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    /**
     * Atualiza a lista de pedidos do cliente.
     * @param pedidos
     */
    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
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
