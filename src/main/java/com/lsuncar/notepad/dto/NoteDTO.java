package com.lsuncar.notepad.dto;

import com.lsuncar.notepad.db.entity.User;
import lombok.Data;

@Data
public class NoteDTO
{
	private Long id;
	private String title;
	private String content;
	private Boolean active;
	private User user;
}
