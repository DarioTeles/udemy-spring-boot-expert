package io.github.darioteles.localizacao.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Define uma entidade Cidade.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_CIDADE")
public class Cidade {

    @Id
    @Column(name = "ID_CIDADE")
    private Long id;

    @Column(name = "NOME", length = 50)
    private String nome;

    @Column(name = "QTD_HABITANTES")
    private Long habitantes;

}
