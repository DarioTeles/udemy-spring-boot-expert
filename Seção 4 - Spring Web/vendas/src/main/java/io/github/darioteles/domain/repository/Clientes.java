package io.github.darioteles.domain.repository;

import io.github.darioteles.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Define uma interface JpaRepository para operações da entidade Cliente.
 */
public interface Clientes extends JpaRepository<Cliente, Integer> {

    //Query Method
    List<Cliente> findByNomeContains(String nome);

    //@Query utilizando HQL - Hibernate Query Language
    @Query(value = "SELECT c FROM Cliente c WHERE c.nome LIKE :nome")
    List<Cliente> findClienteByName(@Param("nome") String nome);

    boolean existsByNome(String nome);

    @Query(value = "SELECT c FROM Cliente c LEFT JOIN FETCH c.pedidos WHERE c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);

}
