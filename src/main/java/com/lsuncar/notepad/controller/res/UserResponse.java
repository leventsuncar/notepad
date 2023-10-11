package com.lsuncar.notepad.controller.res;

import lombok.Data;

@Data
public class UserResponse {
    private Long userId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
