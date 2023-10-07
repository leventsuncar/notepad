package com.lsuncar.notepad.dto;

import lombok.Data;

import java.util.List;

@Data
public class NoteDTO
{
	private Long id;
	private String title;
	private String content;
	private Boolean active;
	private UserDTO owner;
	private List<UserDTO> sharedUsers;
}
