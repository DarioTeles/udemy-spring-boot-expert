package io.github.darioteles.domain.repository;

import io.github.darioteles.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
public interface Clientes extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeContains(String nome);

    @Query(value = "SELECT c FROM Cliente c WHERE c.nome LIKE :nome")
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    boolean existsByNome(String nome);
}
