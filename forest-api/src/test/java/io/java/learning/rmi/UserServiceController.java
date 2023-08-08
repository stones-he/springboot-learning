package io.java.learning.rmi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/user")
public class UserServiceController {
    @GetMapping("/list")
    public List<String> getUser(String user){
        //通过集合工具类返回一个list，包含：张三，李四，王五
        return Stream.of("张三","李四","王五",user).collect(Collectors.toList());
    }
}
