package org.example.userservice.security.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.NoArgsConstructor;
import org.example.userservice.models.Role;
import org.springframework.security.core.GrantedAuthority;
@JsonDeserialize
@NoArgsConstructor
public class CustonGrantedAuthority implements GrantedAuthority {
    private Role role;
    public CustonGrantedAuthority(Role role){
        this.role = role;
    }
    @Override
    public String getAuthority() {
        return role.getName();
    }
}
