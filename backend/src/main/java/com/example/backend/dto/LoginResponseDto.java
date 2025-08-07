package com.example.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponseDto {
    private String token;
    private long expiresIn;
    private String username;
    @JsonProperty("isAdmin")
    private boolean isAdmin;
}

