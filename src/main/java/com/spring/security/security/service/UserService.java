package com.spring.security.security.service;

import com.spring.security.security.co.LoginCO;
import com.spring.security.security.co.RegisterCO;
import com.spring.security.security.dto.LoginResponseDTO;
import com.spring.security.security.dto.RegisterResponseDTO;
import com.spring.security.security.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    RegisterResponseDTO registerUser(RegisterCO registerCO);

    LoginResponseDTO loginUser(LoginCO loginCO);



}
