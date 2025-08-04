package com.spring.security.security.co;

import com.spring.security.security.model.Role;
import com.spring.security.security.model.RolePermission;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data

public class RoleAndPermissionChangeCO {
    private String username;
    private Set<Role> roles;
    private Set<RolePermission.Permission> permissions;
}
