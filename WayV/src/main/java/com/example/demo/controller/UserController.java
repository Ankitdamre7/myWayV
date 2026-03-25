package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/register")

    public User register(@RequestBody User user){

        return service.registerUser(user);

    }

    @PostMapping("/login")

    public User login(

            @RequestParam String email,
            @RequestParam String password){

        return service.login(email,password);

    }

}