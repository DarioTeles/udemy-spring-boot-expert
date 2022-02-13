package io.github.darioteles.domain.repository;

import io.github.darioteles.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Define uma classe @Repository responsável por fazer operações de clientes no banco de dados.
 */
@Repository
public class Clientes {

    //Injeção de dependência do EntityManager
    @Autowired
    private EntityManager entityManager;

    /**
     * Salva um cliente no banco de dados.
     * @param cliente
     * @return cliente
     */
    @Transactional
    public Cliente salvar(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }

    /**
     * Atualiza um cliente no banco de dados.
     * @param cliente
     * @return cliente
     */
    @Transactional
    public Cliente atualizar(Cliente cliente){
        entityManager.merge(cliente);
        return cliente;
    }

    /**
     * Deleta um cliente no banco de dados.
     * @param cliente
     */
    @Transactional
    public void deletar(Cliente cliente){
        if (!entityManager.contains(cliente)){
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    /**
     * Deleta um cliente no banco de dados.
     * @param id
     */
    @Transactional
    public void deletar(Integer id){
        Cliente cliente = entityManager.find(Cliente.class, id);
        deletar(cliente);
    }

    /**
     * Busca clientes por nome.
     * @param nome
     * @return lista de clientes
     */
    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNome(String nome){
        String jpql = "SELECT c FROM Cliente c WHERE c.nome LIKE :nome ";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

    /**
     * Busca todos os clientes.
     * @return lista de clientes
     */
    @Transactional(readOnly = true)
    public List<Cliente> obterTodos(){
        return entityManager.createQuery("FROM Cliente", Cliente.class).getResultList();
    }

}
