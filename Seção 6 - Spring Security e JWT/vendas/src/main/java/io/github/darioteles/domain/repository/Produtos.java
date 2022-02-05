package io.github.darioteles.domain.repository;

import io.github.darioteles.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Define uma interface JpaRepository para operações da entidade Produto.
 */
public interface Produtos extends JpaRepository<Produto, Integer> {
}
