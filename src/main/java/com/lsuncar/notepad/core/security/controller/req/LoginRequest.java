package com.lsuncar.notepad.core.security.controller.req;

import lombok.Data;

@Data
public class LoginRequest
{
	private String username;
	private String password;
}
