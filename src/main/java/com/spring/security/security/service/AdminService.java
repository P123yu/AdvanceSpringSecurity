package com.spring.security.security.service;

import com.spring.security.security.co.RoleChangeCO;
import com.spring.security.security.dto.RoleChangeDTO;
import com.spring.security.security.model.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface AdminService {

    RoleChangeDTO changeRole(RoleChangeCO roleChangeCO);
}
