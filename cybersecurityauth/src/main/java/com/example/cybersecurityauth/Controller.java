package com.example.cybersecurityauth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome";
    }
    @GetMapping("/")
    public String welcome2(){
        return "Welcome Initial Page";
    }
}
