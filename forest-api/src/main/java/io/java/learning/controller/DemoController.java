package io.java.learning.controller;

import io.java.learning.rmi.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/demo")
@CrossOrigin(origins = "http://localhost",allowCredentials = "true")
public class DemoController {
    @Autowired
    private UserClient userClient;
    @RequestMapping("/hello")
    public String hello(){
        return userClient.getUsers("Bob").toString();
    }


    private static List<Map<String,String>> list = new ArrayList<>();
    @RequestMapping("/list")
    public List<Map<String,String>> list(){
        if(!list.isEmpty()) return list;
        Map<String,String> tmp = null;
        for (int i = 0 ; i < 10;i++) {
            tmp = new HashMap<>();
            tmp.put("A","A1"+i);
            tmp.put("B","B1"+i);
            tmp.put("C","C1"+i);
            list.add(tmp);
        }
        return list;
    }

    @RequestMapping(value = "edit",method = RequestMethod.POST)
    public String edit(){
        System.out.println("edit");
        return "OK";
    }
}
