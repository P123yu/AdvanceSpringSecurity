package com.spring.security.security.service.impl;

import com.spring.security.security.co.LoginCO;
import com.spring.security.security.co.RegisterCO;
import com.spring.security.security.dto.LoginResponseDTO;
import com.spring.security.security.dto.RegisterResponseDTO;
import com.spring.security.security.jwt.TokenGenerator;
import com.spring.security.security.model.User;
import com.spring.security.security.repository.UserRepository;
import com.spring.security.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenGenerator tokenGenerator;

    @Override
    public RegisterResponseDTO registerUser(RegisterCO registerCO) {
        User user=User.builder().username(registerCO.getUsername())
                .password(passwordEncoder.encode(registerCO.getPassword()))
                .build();
        user= userRepository.save(user);
        return RegisterResponseDTO.builder()
                .id(user.getId()).message("user created").build();
    }

    @Override
    public LoginResponseDTO loginUser(LoginCO loginCO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                new UsernamePasswordAuthenticationToken(loginCO.getUsername(),loginCO.getPassword());
        Authentication authentication=authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);

        User user=(User) authentication.getPrincipal();
        String jwtToken=tokenGenerator.generateToken(user);

        return LoginResponseDTO.builder()
                .id(user.getId()).token(jwtToken).build();

    }
}
