package com.spring.security.security.controller;

import com.spring.security.security.co.RoleChangeCO;
import com.spring.security.security.dto.RoleChangeDTO;
import com.spring.security.security.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/get")
    public String message(){
        return "hello";
    }

    @PostMapping("/change-role")
    public RoleChangeDTO changeRole(@RequestBody RoleChangeCO roleChangeCO) {
        return adminService.changeRole(roleChangeCO);
    }

    // i want here that if sohan has two roles admin and student
    // and mohan has pure admin role then this method is accessed only by mohan
    // note in mohan and sohan common role is admin that's why they came under
    //  /admin controller but now we can apply method level security

    @PreAuthorize("hasRole('ADMIN') and !hasRole('STUDENT')")
    @GetMapping("/get-admin")
    public String pureAdminMessage(){
        return "hello admin";
    }


}
