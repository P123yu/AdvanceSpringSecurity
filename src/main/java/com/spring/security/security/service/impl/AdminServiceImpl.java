package com.spring.security.security.service.impl;

import com.spring.security.security.co.RoleAndPermissionChangeCO;
import com.spring.security.security.co.RoleChangeCO;
import com.spring.security.security.dto.RoleAndPermissionChangeDTO;
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


    @Override
    public RoleAndPermissionChangeDTO changeRoleAndPermission(RoleAndPermissionChangeCO roleAndPermissionChangeCO) {
        User user = userRepository.findByUsername(roleAndPermissionChangeCO.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + roleAndPermissionChangeCO.getUsername());
        }

        user.setRoles(roleAndPermissionChangeCO.getRoles());

        // Optional: clear existing permissions before assigning new ones
        user.getPermissions().clear();

        if (roleAndPermissionChangeCO.getPermissions() != null) {
            user.setPermissions(roleAndPermissionChangeCO.getPermissions());
        }

        user = userRepository.save(user);

        return RoleAndPermissionChangeDTO.builder()
                .username(user.getUsername())
                .message("Role & Permissions updated successfully")
                .build();
    }


}
