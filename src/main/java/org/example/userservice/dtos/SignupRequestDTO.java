package org.example.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDTO {
    private String name;
    private String email;
    private String password;
}
