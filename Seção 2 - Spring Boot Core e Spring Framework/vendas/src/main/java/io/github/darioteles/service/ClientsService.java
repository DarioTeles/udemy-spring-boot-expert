package io.github.darioteles.service;

import io.github.darioteles.model.Client;
import io.github.darioteles.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientsService {

    private ClientsRepository repository;

    //Exemplo de injeção de dependência de intância da classe ClientsRepository
    @Autowired
    public ClientsService(ClientsRepository repository){
        this.repository = repository;
    }

    /**
     * Salva um cliente.
     * @param client
     */
    public void saveClient(Client client){
        validateClient(client);
        this.repository.persist(client);
    }

    /**
     * Valida um cliente.
     * @param client
     */
    public void validateClient(Client client){
        //Aplica validações
    }
}
