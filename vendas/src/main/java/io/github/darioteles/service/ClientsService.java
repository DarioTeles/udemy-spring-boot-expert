package io.github.darioteles.service;

import io.github.darioteles.model.Client;
import io.github.darioteles.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientsService {

    private ClientsRepository repository;

    @Autowired
    public ClientsService(ClientsRepository repository){
        this.repository = repository;
    }

    public void saveClient(Client client){
        validateClient(client);
        this.repository.persist(client);
    }

    public void validateClient(Client client){
        //Aplica validações
    }
}
