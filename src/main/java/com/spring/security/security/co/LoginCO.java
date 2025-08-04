package com.spring.security.security.co;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class LoginCO {

    @Email(message="enter mail")
    private String username;
    private String password;
}
