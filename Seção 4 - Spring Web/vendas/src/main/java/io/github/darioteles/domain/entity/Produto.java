package io.github.darioteles.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Define um produto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
