package io.github.darioteles.domain.entity;

import javax.persistence.*;

/**
 * Define item de um pedido.
 */
@Entity
@Table
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "PEDIDO_ID")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "PRODUTO_ID")
    private Produto produto;

    @Column
    private Integer quantidade;

    /**
     * Retorna o id do item de pedido.
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Atualiza o item de pedido.
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o pedido correspondente ao item.
     * @return pedido
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * Atualiza o pedido correspondente ao item.
     * @param pedido
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * Retorna o produto correspondente ao item.
     * @return produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * Atualiza o produto correspondente ao item.
     * @param produto
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * Retorna a quantidade do produto correspondente ao item.
     * @return quantidade
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * Atualiza a quantidade do produto correspondente ao item.
     * @param quantidade
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
