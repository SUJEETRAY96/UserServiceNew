package org.example.userservice.controllers;

import lombok.NonNull;
import org.example.userservice.dtos.LoginRequestDTO;
import org.example.userservice.dtos.LogoutRequestDTO;
import org.example.userservice.dtos.SignupRequestDTO;
import org.example.userservice.dtos.SignupResponseDTO;
import org.example.userservice.models.Token;
import org.example.userservice.models.User;
import org.example.userservice.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private final ModelMapper modelMapper;
    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper){
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDTO loginRequestDTO){
        return userService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

    }

    @PostMapping("/signup")
    public SignupResponseDTO signup(@RequestBody SignupRequestDTO signupRequestDTO){
        return mapUserToSignupResponseDTO(userService.signup(signupRequestDTO.getName(),
                signupRequestDTO.getEmail(),
                signupRequestDTO.getPassword()));
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDTO requestDTO){
        userService.logout(requestDTO.getToken());
        return ResponseEntity.ok().build();
    }
    @PostMapping("/validate/{token}")
    public User validateToken(@PathVariable @NonNull String token){
        return userService.validateToken(token);
    }

    public SignupResponseDTO mapUserToSignupResponseDTO(User user) {
        return modelMapper.map(user, SignupResponseDTO.class);
    }
}
