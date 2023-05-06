package com.lsuncar.notepad.controller;

import com.lsuncar.notepad.dto.UserDTO;
import com.lsuncar.notepad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{

	@Autowired
	private UserService userService;

	@PostMapping ( path = "user/signIn" )
	public ResponseEntity<?> signIn ( @RequestBody UserDTO user )
	{
		try
		{
			UserDTO savedUser = userService.signup( user );
			return ResponseEntity.status( HttpStatus.CREATED ).body( savedUser );
		}
		catch ( Exception e )
		{
			return ResponseEntity.badRequest().body( e.getMessage() );
		}
	}
}
