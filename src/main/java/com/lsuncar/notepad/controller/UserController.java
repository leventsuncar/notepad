package com.lsuncar.notepad.controller;

import com.lsuncar.notepad.dto.UserDTO;
import com.lsuncar.notepad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ( "user/" )
public class UserController
{

	@Autowired
	private UserService userService;

	@PostMapping ( path = "signIn" )
	public ResponseEntity<?> signIn ( @RequestBody UserDTO user )
	{
		try
		{
			UserDTO savedUser = userService.signup( user );
			return ResponseEntity.status( HttpStatus.CREATED ).body( savedUser );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return ResponseEntity.badRequest().body( e.getMessage() );
		}
	}

	@GetMapping ( path = "{id}" )
	public ResponseEntity<?> getUserById ( @PathVariable Long id )
	{
		try
		{
			UserDTO user = userService.findUserById( id );
			return ResponseEntity.ok( user );
		}
		catch ( Exception e )
		{
			return ResponseEntity.badRequest().body( e.getMessage() );
		}
	}
}
