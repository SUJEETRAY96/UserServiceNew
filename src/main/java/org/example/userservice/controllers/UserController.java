package org.example.userservice.controllers;

import org.example.userservice.models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    public User login(String email, String password){
        return null;
    }
    public User signup(String name,String email, String password){
        return null;
    }
    public void logout(String token){

    }
}
