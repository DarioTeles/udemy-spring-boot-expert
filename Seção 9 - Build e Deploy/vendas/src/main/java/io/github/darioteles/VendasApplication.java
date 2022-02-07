package io.github.darioteles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class VendasApplication extends SpringBootServletInitializer {

    //Método main responsável por iniciar o Spring Boot
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
