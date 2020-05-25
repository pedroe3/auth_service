package com.sadcrow.auth_service.error;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessage {
    private String message;
    private String error;
    private Date errorDate;
}
