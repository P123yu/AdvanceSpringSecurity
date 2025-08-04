package com.spring.security.security.utility;

import com.spring.security.security.model.Role;
import com.spring.security.security.model.RolePermission.Permission;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.spring.security.security.model.Role.*;
import static com.spring.security.security.model.RolePermission.Permission.*;

public class RolePermissionMapping {

    private static final Map<Role, Set<Permission>> map = Map.of(
            STUDENT, Set.of(STUDENT_READ, STUDENT_WRITE),
            TEACHER, Set.of(TEACHER_READ, TEACHER_WRITE),
            ADMIN, Set.of(ADMIN_WRITE, ADMIN_DELETE, ADMIN_MANAGE)
    );

    public static Set<SimpleGrantedAuthority> getAuthoritiesForRole(Role role) {
        return map.get(role).stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
