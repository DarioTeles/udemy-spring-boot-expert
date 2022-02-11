package io.github.darioteles.localizacao.domain.repository;

import io.github.darioteles.localizacao.domain.entity.Cidade;
import io.github.darioteles.localizacao.domain.repository.projections.CidadeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

    //Query Methods com variantes de String
    List<Cidade> findByNome(String nome);
    List<Cidade> findByNomeStartingWith(String nome);
    List<Cidade> findByNomeEndingWith(String nome);
    List<Cidade> findByNomeContaining(String nome);

    //Variantes do Like
    @Query("SELECT c FROM Cidade c WHERE upper(c.nome) LIKE upper(?1)")
    List<Cidade> findByNomeLike(String nome);

    //Query Methods com valores numéricos
    List<Cidade> findByHabitantes(Long habitantes);
    List<Cidade> findByHabitantesLessThan(Long habitantes);
    List<Cidade> findByHabitantesGreaterThanEqual(Long habitantes);

    //Query Method "Composto"
    List<Cidade> findByHabitantesGreaterThanEqualAndNomeStartingWith(Long habitantes, String nome);

    //Ordenação e Paginação
    List<Cidade> findByNomeLike(String nome, Sort sort);
    Page<Cidade> findByNomeLike(String nome, Pageable pageable);

    //SQL Nativo
    @Query(nativeQuery = true, value = "SELECT c.ID_CIDADE AS id, c.NOME FROM TB_CIDADE AS c WHERE c.NOME =:nome")
    List<CidadeProjection> findByNomeSqlNativo(@Param("nome") String nome);

}
