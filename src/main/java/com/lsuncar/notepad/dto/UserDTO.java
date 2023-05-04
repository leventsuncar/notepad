package com.lsuncar.notepad.dto;

import com.lsuncar.notepad.db.entity.Note;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO
{
	private Long id;
	private String username;
	private String passwd;
	private List<Note> notes;
}
