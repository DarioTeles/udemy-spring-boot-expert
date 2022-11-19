package io.github.darioteles.domain.repository;

import io.github.darioteles.domain.entity.Cliente;
import io.github.darioteles.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Define uma interface JpaRepository para operações da entidade Pedido.
 */
public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

}
