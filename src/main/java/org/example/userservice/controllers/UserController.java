package org.example.userservice.controllers;

import org.example.userservice.dtos.SignupRequestDTO;
import org.example.userservice.dtos.SignupResponseDTO;
import org.example.userservice.models.User;
import org.example.userservice.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public User login(String email, String password){
        return null;
    }

    @PostMapping("/signup")
    public SignupResponseDTO signup(@RequestBody SignupRequestDTO signupRequestDTO){
        return mapUserToSignupResponseDTO(userService.signup(signupRequestDTO.getName(),signupRequestDTO.getEmail(),signupRequestDTO.getPassword()));
    }
    public void logout(String token){

    }

    public SignupResponseDTO mapUserToSignupResponseDTO(User user) {
        return modelMapper.map(user, SignupResponseDTO.class);
    }
}
