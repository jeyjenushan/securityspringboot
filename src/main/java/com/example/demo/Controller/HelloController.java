package com.example.demo.Controller;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class HelloController {
    @GetMapping("/")
    public String greetin(HttpServletRequest request){
        return "My name is jenushan"+request.getSession().getId();
    }

}
