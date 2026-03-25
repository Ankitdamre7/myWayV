package com.example.demo.entity;




import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
   @Email
    private String email;
    private String password;

  
    private Boolean driverMode=false;// DRIVER or PASSENGER

    public User(){}

    public User(String username,String email,String password,Boolean role){
        this.username=username;
        this.email=email;
        this.password=password;
      this.driverMode=role;
    }

    public Long getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public Boolean getDriverMode(){
        return driverMode;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public void setRole(Boolean role){
        this.driverMode=role;
    }
}