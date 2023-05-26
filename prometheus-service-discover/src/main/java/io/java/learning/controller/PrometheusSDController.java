package io.java.learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("prom")
public class PrometheusSDController {
    @GetMapping("servers")
    public String server(){

        return "[{\"targets\":[\"10.60.5.34:9012\",\"10.60.5.34:9022\",\"10.60.5.34:9032\"]}]";
    }
}
