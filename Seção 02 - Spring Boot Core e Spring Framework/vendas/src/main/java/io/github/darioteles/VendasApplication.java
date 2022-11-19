package io.github.darioteles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasApplication {

    //Exemplo de injeção de dependência a partir de uma classe @Configuration
    @Autowired
    @Qualifier("applicationName")
    private String applicationName;

    //Exemplo de injeção de dependência a partir de um arquivo .properties
    @Value("${application.name}")
    private String applicationNamePropeties;

    //Endpoint
    @GetMapping("/MyConfiguration/applicationName")
    public String showApplicationName(){
        return applicationName;
    }

    //Endpoint
    @GetMapping("/application.propeties/applicationName")
    public String showApplicationNamePropeties(){
        return applicationNamePropeties;
    }

    //Método main responsável por iniciar o Spring Boot
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
