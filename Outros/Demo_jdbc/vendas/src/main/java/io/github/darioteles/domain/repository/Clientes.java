package io.github.darioteles.domain.repository;

import io.github.darioteles.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Define uma classe @Repository responsável por fazer operações de clientes no banco de dados.
 */
@Repository
public class Clientes {

    //Scripts SQL
    private static String INSERT = "INSERT INTO CLIENTE (NOME) VALUES (?)";
    private static String SELECT_ALL = "SELECT * FROM CLIENTE";
    private static String UPDATE = "UPDATE CLIENTE SET NOME = ? WHERE ID = ?";
    private static String DELETE = "DELETE FROM CLIENTE WHERE ID = ?";

    //Injeção de dependênica do JDBC
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Salva um cliente no banco de dados.
     * @param cliente
     * @return cliente
     */
    public Cliente salvar(Cliente cliente){
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    /**
     * Atualiza um cliente no banco de dados.
     * @param cliente
     * @return cliente
     */
    public Cliente atualizar(Cliente cliente){
        jdbcTemplate.update(UPDATE, new Object[]{cliente.getNome(), cliente.getId()});
        return cliente;
    }

    /**
     * Deleta um cliente no banco de dados.
     * @param cliente
     */
    public void deletar(Cliente cliente){
        jdbcTemplate.update(DELETE, cliente.getId());
    }

    /**
     * Deleta um cliente no banco de dados.
     * @param id
     */
    public void deletar(Integer id){
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    /**
     * Busca clientes por nome.
     * @param nome
     * @return lista de clientes
     */
    public List<Cliente> buscarPorNome(String nome){
        return jdbcTemplate.query(SELECT_ALL.concat(" where nome like ?"), new Object[]{"%" + nome + "%"}, obterClienteMapper());
    }

    /**
     * Busca todos os clientes.
     * @return lista de clientes
     */
    public List<Cliente> obterTodos(){
        return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
    }

    private RowMapper<Cliente> obterClienteMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id =  resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }
}
