package com.lsuncar.notepad.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lsuncar.notepad.db.entity.Note;
import lombok.Data;

import java.util.List;

/**
 * DTO for {@link Note}
 */
@Data
public class NoteDTO extends BaseDTO {
    private Long id;
    private String title;
    private String content;
    private UserDTO owner;
    @JsonIgnore
    private List<UserSharedNotesDTO> userSharedNotes;
}