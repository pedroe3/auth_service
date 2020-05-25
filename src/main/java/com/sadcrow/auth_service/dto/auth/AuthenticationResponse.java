package com.sadcrow.auth_service.dto.auth;

import lombok.Getter;

@Getter
public class AuthenticationResponse {

    private final String token;

    public AuthenticationResponse(String jwt) {
        this.token = jwt;
    }
}
