package com.spring.security.security.co;

import jakarta.validation.constraints.Email;
import lombok.*;

@Data
public class RegisterCO {

    @Email(message="enter mail")
    private String username;
    private String password;

}
