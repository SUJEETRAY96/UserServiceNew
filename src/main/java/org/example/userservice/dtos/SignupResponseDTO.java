package org.example.userservice.dtos;

import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.example.userservice.models.Role;

import java.util.List;
@Getter
@Setter
public class SignupResponseDTO {
    private String name;
    private String email;
    private boolean isEmailVerified;
}
