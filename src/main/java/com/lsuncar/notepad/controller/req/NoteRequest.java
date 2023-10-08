package com.lsuncar.notepad.controller.req;

import lombok.Data;

@Data
public class NoteRequest {
    private String title;
    private String content;
    private Long userId;
}
