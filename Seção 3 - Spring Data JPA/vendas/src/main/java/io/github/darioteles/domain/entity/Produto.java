package io.github.darioteles.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Define um produto.
 */
@Entity
@Table
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 100)
    private String descricao;

    @Column(name = "PRECO_UNITARIO")
    private BigDecimal preco;

    /**
     * Retorna o id do produto.
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Atualiza o id do produto.
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna a descrição do produto.
     * @return descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Atualiza a descrição do produto.
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna o preço do do produto.
     * @return produto
     */
    public BigDecimal getPreco() {
        return preco;
    }

    /**
     * Atualiza o preço do produto.
     * @param preco
     */
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

}
