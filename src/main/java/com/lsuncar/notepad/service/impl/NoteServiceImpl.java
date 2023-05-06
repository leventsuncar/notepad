package com.lsuncar.notepad.service.impl;

import com.lsuncar.notepad.controller.req.NoteRequest;
import com.lsuncar.notepad.core.exception.EntityNotFoundException;
import com.lsuncar.notepad.db.dao.NoteDAO;
import com.lsuncar.notepad.db.dao.UserDAO;
import com.lsuncar.notepad.dto.NoteDTO;
import com.lsuncar.notepad.dto.UserDTO;
import com.lsuncar.notepad.dto.mapper.NoteMapper;
import com.lsuncar.notepad.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class NoteServiceImpl implements NoteService
{
	@Autowired
	private NoteDAO noteDAO;

	@Autowired
	private UserDAO userDAO;

	private NoteMapper getMapper ()
	{
		return NoteMapper.INSTANCE;
	}

	@Override
	public List<NoteDTO> findNoteByUserId ( Long userId ) throws Exception
	{
		try
		{
			List<NoteDTO> notes = noteDAO.findNoteByUserId( userId );
			if ( nonNull( notes ) && !notes.isEmpty() )
				return notes;
			else
				throw new EntityNotFoundException( "Notes not found" );
		}
		catch ( Exception e )
		{
			throw e;
		}
	}

	@Override
	public List<NoteDTO> findDeletedNoteByUserId ( Long userId ) throws Exception
	{
		try
		{
			List<NoteDTO> notes = noteDAO.findDeletedNoteByUserId( userId );
			if ( nonNull( notes ) && !notes.isEmpty() )
				return notes;
			else
				throw new EntityNotFoundException( "Notes not found" );
		}
		catch ( Exception e )
		{
			throw e;
		}
	}

	@Override
	public Boolean deleteNoteById ( Long noteId ) throws Exception
	{
		try
		{
			NoteDTO noteDTO = noteDAO.findNoteById( noteId );
			if ( nonNull( noteDTO ) )
			{
				noteDAO.deleteNoteById( noteId );
				return true;
			}
			else
				throw new EntityNotFoundException( "Note not found" );
		}
		catch ( Exception e )
		{
			throw e;
		}

	}

	@Override
	public Boolean deleteNotePermanentlyById ( Long noteId ) throws Exception
	{
		try
		{
			NoteDTO noteDTO = noteDAO.findNoteById( noteId );
			if ( nonNull( noteDTO ) )
			{
				noteDAO.deleteNotePermanentlyById( noteId );
				return true;
			}
			else
				throw new EntityNotFoundException( "Note not found" );
		}
		catch ( Exception e )
		{
			throw e;
		}
	}

	@Override
	public NoteDTO save ( NoteRequest note ) throws Exception
	{
		try
		{
			UserDTO user = userDAO.findUserById( note.getUserId() );
			if ( isNull( user ) )
			{
				throw new EntityNotFoundException( "User not found" );
			}
			NoteDTO noteDTO = new NoteDTO();
			noteDTO.setContent( note.getContent() );
			noteDTO.setTitle( note.getTitle() );
			noteDTO.setUser( user );
			NoteDTO savedNote = noteDAO.save( noteDTO );
			return savedNote;
		}
		catch ( Exception e )
		{
			throw e;
		}
	}
}
