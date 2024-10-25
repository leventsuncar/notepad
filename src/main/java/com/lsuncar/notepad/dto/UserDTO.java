package com.lsuncar.notepad.dto;

import com.lsuncar.notepad.db.entity.User;
import lombok.Data;

/**
 * DTO for {@link User}
 */
@Data
public class UserDTO extends BaseDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
}