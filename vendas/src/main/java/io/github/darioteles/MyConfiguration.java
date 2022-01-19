package io.github.darioteles;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("development")
public class MyConfiguration {

    @Bean(name = "applicationName")
    public String applicationName(){
        return "Sistema de Vendas";
    }

    @Bean
    public CommandLineRunner exeutar(){
        return args -> { System.out.println("Rodando a configuração de desenvolvimento");};
    }
}
