package org.example.userservice.services;

import org.example.userservice.models.User;
import org.example.userservice.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//Should be interface.
@Service
public class UserService {
    private BCryptPasswordEncoder passwordEncoder;
    private UserRepo userRepo;
    @Autowired
    public UserService(BCryptPasswordEncoder passwordEncoder,UserRepo userRepo){
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }
    public User signup(String name, String email, String password){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setHashedPassword(passwordEncoder.encode(password));
        User savedUser = userRepo.save(user);
        return savedUser;
    }
}
