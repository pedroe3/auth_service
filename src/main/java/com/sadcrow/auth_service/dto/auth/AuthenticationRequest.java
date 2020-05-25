package com.sadcrow.auth_service.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AuthenticationRequest {

    @NotNull(message = "userName is mandatory")
    private String userName;

    @NotNull(message = "password is mandatory")
    private String password;
}
