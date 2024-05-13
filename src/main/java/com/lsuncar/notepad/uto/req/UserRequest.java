package com.lsuncar.notepad.uto.req;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String email;
    private String emailHost;
    private String password;
    private String repeatPassword;
    private String firstname;
    private String lastname;
}
