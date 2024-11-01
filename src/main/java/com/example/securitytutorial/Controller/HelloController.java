package com.example.securitytutorial.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String  hello(){
        return "Hello";
    }

    @GetMapping("/helloUser")
    public String getUser(){
        return "Hi User you are authenticated";
    }

    @GetMapping("/helloAdmin")
    public String getAdmin(){
        return "Hi Admin you are authenticated";
    }
}
