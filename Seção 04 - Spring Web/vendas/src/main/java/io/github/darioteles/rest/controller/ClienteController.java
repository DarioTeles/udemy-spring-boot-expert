package io.github.darioteles.rest.controller;

import io.github.darioteles.domain.entity.Cliente;
import io.github.darioteles.domain.repository.Clientes;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Define um Controller Spring responsável pelas operações da entidade Cliente.
 */
@RestController
@RequestMapping("api/clientes")
public class ClienteController {

    // Injeção de dependência do objeto repository do tipo Clientes.
    private Clientes clientes;

    public ClienteController(Clientes clientes){
        this.clientes = clientes;
    }

//    /**
//     * Exemplo de requisição GET com parâmetros e ResponseEntity
//     */
//    @RequestMapping(value = "{id}", method = RequestMethod.GET) é substituído pelo @GetMapping
//    @GetMapping("{id}")
//    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Integer id){
//        Optional<Cliente> cliente = clientes.findById(id);
//        if (cliente.isPresent()){
//            return ResponseEntity.ok(cliente.get());
//        }
//        return ResponseEntity.notFound().build();
//    }

    /**
     * Requisição GET sem ResponseEntity (Refatorado).
     */
    @GetMapping("{id}")
    public Cliente getClienteById(@PathVariable("id") Integer id){
        return clientes.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente){
        return clientes.save(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id){
        clientes.findById(id)
                .map(cliente -> {clientes.delete(cliente); return cliente;}) // x -> {} é uma função anônima
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Integer id, @RequestBody Cliente cliente){
        clientes.findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    @GetMapping
    public List<Cliente> find(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                                .matching()
                                .withIgnoreCase()
                                .withStringMatcher(
                                        ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return clientes.findAll(example);
    }

}
