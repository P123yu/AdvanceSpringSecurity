package com.spring.security.security.co;

import com.spring.security.security.model.Role;
import lombok.Data;
import java.util.Set;

@Data
public class RoleChangeCO {
    private String username;
    private Set<Role> roles;
}
