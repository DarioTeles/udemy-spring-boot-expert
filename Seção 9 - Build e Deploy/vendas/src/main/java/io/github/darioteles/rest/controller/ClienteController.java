package io.github.darioteles.rest.controller;

import io.github.darioteles.domain.entity.Cliente;
import io.github.darioteles.domain.repository.Clientes;
import io.swagger.annotations.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * Define um Controller Spring responsável pelas operações da entidade Cliente.
 */
@RestController
@RequestMapping("api/clientes")
@Api("API de Operações de Clientes")
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
    @ApiOperation("Obtêm detalhes de um cliente.") //Annotation Swagger
    @ApiResponses({ //Annotation Swagger
            @ApiResponse(code = 200, message = "Cliente encontrado."),
            @ApiResponse(code = 404, message = "Cliente não encontrado pelo o ID informado.")
    })
    public Cliente getClienteById(@PathVariable("id") @ApiParam("ID do Cliente.") Integer id){ //@ApiParam é uma Annotation Swagger
        return clientes.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salva um novo cliente.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente salvo com sucesso."),
            @ApiResponse(code = 400, message = "Erro de validação.")
    })
    public Cliente save(@RequestBody @Valid Cliente cliente){//@Valid especifica que o objeto cliente deve ser validado.
        return clientes.save(cliente);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Integer id, @RequestBody @Valid Cliente cliente){
        clientes.findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id){
        clientes.findById(id)
                .map(cliente -> {clientes.delete(cliente); return cliente;}) // x -> {} é uma função anônima
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
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
