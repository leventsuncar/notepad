package com.lsuncar.notepad.dto;

import com.lsuncar.notepad.db.entity.UserSharedNote;
import lombok.Data;

/**
 * DTO for {@link UserSharedNote}
 */
@Data
public class UserSharedNotesDTO extends BaseDTO {
    private Long createdAt;
    private Long updatedAt;
    private Boolean active;
    private Long id;
    private UserDTO user;
    private NoteDTO note;
}