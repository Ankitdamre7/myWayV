package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public User registerUser(User user){

        return repo.save(user);
    }

    public User login(String email,String password){

        Optional<User> user=repo.findByEmail(email);

        if(user.isPresent() && user.get().getPassword().equals(password)){
            return user.get();
        }

        return null;
    }

}