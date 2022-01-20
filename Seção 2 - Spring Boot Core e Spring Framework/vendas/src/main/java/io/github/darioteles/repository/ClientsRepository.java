package io.github.darioteles.repository;

import io.github.darioteles.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public class ClientsRepository {

    /**
     * Persiste um cliente no banco de dados.
     * @param client
     */
    public void persist(Client client) {
        //Acessa a base e salva o cliente
    }

}
