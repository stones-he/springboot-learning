package io.java.learning;

import org.apereo.cas.client.boot.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCasClient
public class CasMain {
    public static void main(String[] args) {
        SpringApplication.run(CasMain.class, args);
    }
}