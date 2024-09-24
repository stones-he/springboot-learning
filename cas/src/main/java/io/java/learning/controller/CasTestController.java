package io.java.learning.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CasTestController {
    @Value("${cas.server.logout-url:https://localhost:8443/cas/logout}")
    private String logoutUrl ;
    @GetMapping("/hello")
    public String hello() {
        return "Hello, CAS!";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:"+logoutUrl;
    }
}
