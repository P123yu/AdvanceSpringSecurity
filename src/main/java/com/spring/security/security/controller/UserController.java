package com.spring.security.security.controller;

import com.spring.security.security.co.LoginCO;
import com.spring.security.security.co.RegisterCO;
import com.spring.security.security.dto.LoginResponseDTO;
import com.spring.security.security.dto.RegisterResponseDTO;
import com.spring.security.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterCO registerCO){
        RegisterResponseDTO registerResponseDTO=userService.registerUser(registerCO);
        return ResponseEntity.ok(registerResponseDTO);
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginCO loginCO){
        LoginResponseDTO loginResponseDTO=userService.loginUser(loginCO);
        return ResponseEntity.ok(loginResponseDTO);
    }








}
