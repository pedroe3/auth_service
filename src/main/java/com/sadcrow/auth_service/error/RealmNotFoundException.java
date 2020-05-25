package com.sadcrow.auth_service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RealmNotFoundException extends RuntimeException {
    public RealmNotFoundException(String message) {
        super(message);
    }
}
