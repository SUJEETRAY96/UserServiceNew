package org.example.userservice.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.userservice.models.Token;
import org.example.userservice.models.User;
import org.example.userservice.repositories.TokenRepo;
import org.example.userservice.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

//Should be interface.
@Service
public class UserService {
    private BCryptPasswordEncoder passwordEncoder;
    private UserRepo userRepo;
    private TokenRepo tokenRepo;
    @Autowired
    public UserService(BCryptPasswordEncoder passwordEncoder,
                       UserRepo userRepo,
                       TokenRepo tokenRepo){
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
    }
    public User signup(String name, String email, String password){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setHashedPassword(passwordEncoder.encode(password));
        return userRepo.save(user);
    }

    public Token login(String email, String password) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();
        if(passwordEncoder.matches(password, user.getHashedPassword())){
            Token token = new Token();
            token.setUser(user);
            token.setValue(RandomStringUtils.randomAlphanumeric(128));
            LocalDate date = LocalDate.now().plusDays(1);
            Date expiryDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
            token.setExpiryDate(expiryDate);
            return tokenRepo.save(token);
        }
        return null;

    }

    public void logout(String token) {
        Optional<Token> tokenOptional = tokenRepo.findByValueAndDeletedEquals(token, false);
        if(tokenOptional.isEmpty()){
            throw new RuntimeException("Token not found");
        }
        Token token1 = tokenOptional.get();
        token1.setDeleted(true);
        tokenRepo.save(token1);
    }
}
