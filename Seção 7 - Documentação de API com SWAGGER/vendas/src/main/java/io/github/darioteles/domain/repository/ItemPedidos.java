package io.github.darioteles.domain.repository;

import io.github.darioteles.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Define uma interface JpaRepository para operações da entidade ItemPedido.
 */
public interface ItemPedidos extends JpaRepository<ItemPedido, Integer> {
}
