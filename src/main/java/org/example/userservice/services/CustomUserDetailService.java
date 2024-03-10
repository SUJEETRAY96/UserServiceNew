package org.example.userservice.services;

import org.example.userservice.models.User;
import org.example.userservice.repositories.UserRepo;
import org.example.userservice.security.models.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepo userRepo;
    @Autowired
    public CustomUserDetailService(UserRepo userRepo){
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found"+username);
        }
        return new CustomUserDetails(user.get());
    }
}
