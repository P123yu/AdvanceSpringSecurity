package com.spring.security.security.controller;

import com.spring.security.security.co.RoleChangeCO;
import com.spring.security.security.dto.RoleChangeDTO;
import com.spring.security.security.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final AdminService adminService;

    @GetMapping("/get-student")
    public String studentMessage(){
        return "hello students";
    }


}
