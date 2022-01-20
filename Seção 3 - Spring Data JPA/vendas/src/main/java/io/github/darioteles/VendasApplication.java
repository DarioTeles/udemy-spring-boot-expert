package io.github.darioteles;

import io.github.darioteles.domain.entity.Cliente;
import io.github.darioteles.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    //Método main responsável por iniciar o Spring Boot
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

    //@Bean responsável por executar comandos no console Spring Boot
    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {

            System.out.println("Salvando clientes:");
            clientes.save(new Cliente("Douglas"));
            clientes.save(new Cliente("Outro Cliente"));

            System.out.println("Listando todos os clientes:");
            List<Cliente> todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);

            boolean existe = clientes.existsByNome("Douglas");
            System.out.println("Existe algum cliente chamado Douglas? " + existe);

            System.out.println("Atualizando clientes:");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientes.save(c);
            });
            todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);

            System.out.println("Buscando clientes por nome (QueryMethod):");
            clientes.findByNomeContains("Cli").forEach(System.out::println);

            System.out.println("Buscando clientes por nome (@Query):");
            clientes.encontrarPorNome("Cli").forEach(System.out::println);

            System.out.println("Deletando clientes:");
            clientes.findAll().forEach(c -> {
                clientes.delete(c);
            });

            todosClientes = clientes.findAll();
            if (todosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado.");
            }else {
                todosClientes.forEach(System.out::println);
            }
        };
    }
}
