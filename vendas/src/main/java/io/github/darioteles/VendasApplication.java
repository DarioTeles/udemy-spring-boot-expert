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

    @Autowired
    @Qualifier("applicationName")
    private String applicationName;

    @Value("${application.name}")
    private String applicationNamePropeties;

    @GetMapping("/MyConfiguration/applicationName")
    public String showApplicationName(){
        return applicationName;
    }

    @GetMapping("/application.propeties/applicationName")
    public String showApplicationNamePropeties(){
        return applicationNamePropeties;
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
