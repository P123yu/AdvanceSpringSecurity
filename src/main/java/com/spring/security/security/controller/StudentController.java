package com.spring.security.security.controller;

import com.spring.security.security.model.User;
import com.spring.security.security.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final AdminService adminService;


    @GetMapping("/get-student")
    public String studentMessage(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "hello "+user.getUsername();
    }


// note here the role which have permission of 'student:write'  only access this method
    @GetMapping("/get-student-info")
//    @PreAuthorize("hasAuthority('appointment:write') or #doctorId == authentication.principal.id")
    @PreAuthorize("hasAuthority('student:write')")
    public String studentMessageInfo(){
        return "hello i am student and i am writing";
    }




}
