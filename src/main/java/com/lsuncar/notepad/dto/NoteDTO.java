package com.lsuncar.notepad.dto;

import com.lsuncar.notepad.db.entity.Note;
import lombok.Data;

/**
 * DTO for {@link Note}
 */
@Data
public class NoteDTO extends BaseDTO {
    private Long id;
    private String title;
    private String content;
    private UserDTO owner;
}