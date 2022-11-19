package io.github.darioteles.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Define um pedido de compra.
 */
@Entity
@Table
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;

    @Column(name = "DATA_PEDIDO")
    private LocalDate dataPedido;

    @Column(precision = 20, scale = 2)
    private BigDecimal total;

    @OneToMany (mappedBy = "pedido")
    private List<ItemPedido> itens;

    /**
     * Retorna o id do pedido.
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Atualiza o id do pedido.
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o cliente do pedido.
     * @return cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Atualiza o cliente do pedido.
     * @param cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Retorna a data do pedido.
     * @return dataPedido
     */
    public LocalDate getDataPedido() {
        return dataPedido;
    }

    /**
     * Atualiza a data do pedido.
     * @param dataPedido
     */
    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    /**
     * Retorna o valor total do pedido.
     * @return total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Atualiza o valor total do pedido.
     * @param total
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * Retorna uma lista de itens do pedido.
     * @return itens
     */
    public List<ItemPedido> getItens() {
        return itens;
    }

    /**
     * Atualiza uma lista de itens do pedido.
     * @param itens
     */
    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", dataPedido=" + dataPedido +
                ", total=" + total +
                '}';
    }
}
