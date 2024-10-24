package com.example.demo.Controller;

import com.example.demo.model.User;
import com.example.demo.service.uservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private uservice serivce;

    @PostMapping("/register")
public User Register(@RequestBody User users){
    return serivce.register(users);
    }

    //after we finish the security config create default login form 3rd step in jwt
    @PostMapping("/login")
    public String Login(@RequestBody User users){
 return serivce.verify(users);
    }
}
