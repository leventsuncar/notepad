package com.lsuncar.notepad.dto;

import lombok.Data;

@Data
public class BaseDTO
{
	private Long createdAt;
	private Long updatedAt;
	private Boolean active;
}
