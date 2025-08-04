package com.spring.security.security.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponseDTO {

    private Long id;
    private String message;

}
