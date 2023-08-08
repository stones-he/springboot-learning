package io.java.learning.controller;

import io.java.learning.rmi.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private UserClient userClient;
    @RequestMapping("/hello")
    public String hello(){
        return userClient.getUsers("Bob").toString();
    }
}
