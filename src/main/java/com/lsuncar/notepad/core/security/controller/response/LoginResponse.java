package com.lsuncar.notepad.core.security.controller.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private Long userId;
}
