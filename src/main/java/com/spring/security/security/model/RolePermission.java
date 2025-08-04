package com.spring.security.security.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class RolePermission {
    @Getter
    @RequiredArgsConstructor
    public enum Permission {
        STUDENT_READ("student:read"),
        STUDENT_WRITE("student:write"),
        TEACHER_READ("teacher:read"),
        TEACHER_WRITE("teacher:write"),
        ADMIN_WRITE("admin:write"),
        ADMIN_DELETE("admin:delete"),
        ADMIN_MANAGE("admin:manage");

        private final String permission;
    }
}