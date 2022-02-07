package io.github.darioteles.domain.repository;

import io.github.darioteles.domain.entity.Cliente;
import io.github.darioteles.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Define uma interface JpaRepository para operações da entidade Pedido.
 */
public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

    @Query("SELECT p from Pedido p LEFT JOIN FETCH p.itens WHERE p.id =:id ")
    Optional<Pedido> findByIdFetchItens(Integer id);

}
