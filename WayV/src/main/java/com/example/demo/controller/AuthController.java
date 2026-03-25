package com.example.demo.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.config.JwtUtil;
import com.example.demo.dto.LoginDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin // allow frontend
public class AuthController {
	@Autowired
	JwtUtil jwtUtil;

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepo userRepo,PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder=passwordEncoder;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        // simple logic (you can connect DB later)
        if(user.getEmail().isEmpty() || user.getPassword().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message","Please fill all fields"));
        }
        Optional<User> user1=userRepo.findByEmail(user.getEmail());
        // simulate success
        Optional<User> user2=userRepo.findByUsername(user.getUsername());

        if(user1.isPresent())
        {
        	          return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message","user exists"));
 
        }
        if(user2.isPresent())
        {
        	          return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message","username exists"));
 
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","Registration Successful"));
 
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginUser) {

     System.out.println("hi"+loginUser.getIdentifier());
        if(loginUser.getIdentifier().isEmpty() || loginUser.getPassword().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message","Please fill all fields"));
        }
        Optional<User> user = userRepo.findByEmail(loginUser.getIdentifier());

        if (!user.isPresent()) {
            user = userRepo.findByUsername(loginUser.getIdentifier());
             if(!user.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","user not found"
            		));
        }

        // ✅ CORRECT PASSWORD CHECK
        if(user.isPresent())
        if (!passwordEncoder.matches(loginUser.getPassword(), user.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message","Invalid password"));
        }
       
        	

        // ✅ Generate JWT token
        String token=jwtUtil.generateToken(user.get().getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
        		"message","login Succesful",
        		"token",token
        		));
    }
}