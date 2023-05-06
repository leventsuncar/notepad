package com.lsuncar.notepad.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class NoteDTO
{
	private Long id;
	private String title;
	private String content;
	private Boolean active;
	@JsonIgnore
	private UserDTO user;
}
