package com.lsuncar.notepad.controller;

import com.lsuncar.notepad.controller.req.NoteRequest;
import com.lsuncar.notepad.dto.NoteDTO;
import com.lsuncar.notepad.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ( "note/" )
public class NoteController
{
	@Autowired
	private NoteService noteService;


	@PostMapping( "add" )
	public ResponseEntity<?> addNote ( @RequestBody NoteRequest noteDTO )
	{
		try
		{
			NoteDTO savedNote = noteService.save( noteDTO );
			return ResponseEntity.status( HttpStatus.CREATED ).body( savedNote );
		}
		catch ( Exception e )
		{
			return ResponseEntity.badRequest().body( e.getMessage() );
		}
	}
}
