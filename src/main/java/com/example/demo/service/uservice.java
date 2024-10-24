package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class uservice {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JWTService jwtService;
    @Autowired
    AuthenticationManager manager;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
    public User register(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public String verify(User users) {
        Authentication authentication=
                manager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(users.getUsername());
        }
        else{
            return "fail";
        }

    }
}
