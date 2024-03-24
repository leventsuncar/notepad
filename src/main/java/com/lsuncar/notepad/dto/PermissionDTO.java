package com.lsuncar.notepad.dto;

import com.lsuncar.notepad.db.entity.Permission;
import com.lsuncar.notepad.enumeration.AccessLevel;
import lombok.Data;

/**
 * DTO for {@link Permission}
 */
@Data
public class PermissionDTO extends BaseDTO {
    private Long id;
    private UserDTO user;
    private CompanyDTO company;
    private NoteDTO note;
    private AccessLevel accessLevel;
}