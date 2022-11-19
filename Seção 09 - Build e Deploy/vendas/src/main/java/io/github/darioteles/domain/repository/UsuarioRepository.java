package io.github.darioteles.domain.repository;

import io.github.darioteles.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Define uma interface JpaRepository para operações da entidade Usuario.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);
}
