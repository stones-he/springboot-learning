package io.java.learning.springbootlearning.oauth.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
//    @RequestMapping("/login")
    public String login(String username, String password) {
        System.out.println("login success!"+ username + " " + password);
        return "success";
    }
}
