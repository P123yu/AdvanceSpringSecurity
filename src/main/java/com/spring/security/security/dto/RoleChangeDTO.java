package com.spring.security.security.dto;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class RoleChangeDTO {
    private String username;
    private String message;
}
