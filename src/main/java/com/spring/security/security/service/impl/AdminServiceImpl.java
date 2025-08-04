package com.spring.security.security.service.impl;

import com.spring.security.security.co.RoleChangeCO;
import com.spring.security.security.dto.RoleChangeDTO;
import com.spring.security.security.model.Role;
import com.spring.security.security.model.User;
import com.spring.security.security.repository.UserRepository;
import com.spring.security.security.service.AdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    @Override
    public RoleChangeDTO changeRole(RoleChangeCO roleChangeCO) {
        User user = userRepository.findByUsername(roleChangeCO.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + roleChangeCO.getUsername());
        }
        user.setRoles(roleChangeCO.getRoles());
        user=userRepository.save(user);
        return RoleChangeDTO.builder().username(user.getUsername())
                .message("Role changed successfully").build();
    }

}
