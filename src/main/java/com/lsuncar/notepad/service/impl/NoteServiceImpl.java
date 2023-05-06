package com.lsuncar.notepad.service.impl;

import com.lsuncar.notepad.core.exception.EntityNotFoundException;
import com.lsuncar.notepad.db.dao.NoteDAO;
import com.lsuncar.notepad.db.entity.Note;
import com.lsuncar.notepad.dto.NoteDTO;
import com.lsuncar.notepad.dto.mapper.NoteMapper;
import com.lsuncar.notepad.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class NoteServiceImpl implements NoteService
{
	@Autowired
	private NoteDAO noteDAO;

	private NoteMapper getMapper ()
	{
		return NoteMapper.INSTANCE;
	}

	@Override
	public List<NoteDTO> findNoteByUserId ( Long userId ) throws Exception
	{
		try
		{
			List<Note> notes = noteDAO.findNoteByUserId( userId );
			if ( nonNull( notes ) && !notes.isEmpty() )
			{
				List<NoteDTO> noteDTOList = new ArrayList<>();
				for ( Note note : notes )
				{
					NoteDTO noteDTO = getMapper().toNoteDTO( note );
					noteDTOList.add( noteDTO );
				}
				return noteDTOList;
			}
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
			List<Note> notes = noteDAO.findDeletedNoteByUserId( userId );
			if ( nonNull( notes ) && !notes.isEmpty() )
			{
				List<NoteDTO> noteDTOList = new ArrayList<>();
				for ( Note note : notes )
				{
					NoteDTO noteDTO = getMapper().toNoteDTO( note );
					noteDTOList.add( noteDTO );
				}
				return noteDTOList;
			}
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
		//TODO find note by id!!
		return null;
	}

	@Override
	public Boolean deleteNotePermanentlyById ( Long noteId ) throws Exception
	{
		//TODO find note by id!!
		return null;
	}

	@Override
	public NoteDTO save ( NoteDTO noteDTO ) throws Exception
	{
		try
		{
			Note note = getMapper().toNote( noteDTO );
			Note savedNote = noteDAO.save( note );
			NoteDTO savedNoteDTO = getMapper().toNoteDTO( savedNote );
			return savedNoteDTO;
		}
		catch ( Exception e )
		{
			throw e;
		}
	}
}
