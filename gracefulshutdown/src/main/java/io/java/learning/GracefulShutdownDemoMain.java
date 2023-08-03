package io.java.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GracefulShutdownDemoMain {
    public static void main(String[] args) {
        SpringApplication.run(GracefulShutdownDemoMain.class, args);
    }
}