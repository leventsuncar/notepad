package com.lsuncar.notepad.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lsuncar.notepad.db.entity.Note;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO
{
	private Long id;
	private String username;
	private String password;
	private String email;
	@JsonIgnore
	private List<Note> ownedNotes;
}
