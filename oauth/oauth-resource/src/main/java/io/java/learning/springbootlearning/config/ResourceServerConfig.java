package io.java.learning.springbootlearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeHttpRequests) -> {
            authorizeHttpRequests.requestMatchers("/messages/**").authenticated();
        }).oauth2ResourceServer((oauth2ResourceServer) -> {
            oauth2ResourceServer.jwt((jwt) -> jwt.decoder(JwtDecoders.fromIssuerLocation("http://localhost:9000/oauth/token")));
        });

//        http.mvcMatcher("/messages/**").authorizeRequests().mvcMatchers("/messages/**").access("hasAuthority('SCOPE_message.read')").and().oauth2ResourceServer().jwt();
        return http.build();
    }

}