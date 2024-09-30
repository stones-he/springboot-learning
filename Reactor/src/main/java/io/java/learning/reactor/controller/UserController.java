package io.java.learning.reactor.controller;

import io.java.learning.reactor.pojo.TUser;
import io.java.learning.reactor.repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @RequestMapping("/list")
    public Flux<TUser> list() {
//        Flux.from(userRepository.findAll()).delayElements(Duration.ofSeconds(1)).subscribe(System.out::println);
        return userRepository.findAll();//.delayElements(Duration.ofSeconds(1));
    }
}
