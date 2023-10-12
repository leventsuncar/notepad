package com.lsuncar.notepad.controller.res;

import lombok.Data;

@Data
public class NoteResponse {
    private Long id;
    private String title;
    private String content;
    private Boolean active;
    private UserResponse owner;
    private Boolean isOwner;
}
