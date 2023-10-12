package com.lsuncar.notepad.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lsuncar.notepad.db.entity.User;
import lombok.Data;

import java.util.List;

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
    @JsonIgnore
    private List<UserSharedNotesDTO> userSharedNotes;
}