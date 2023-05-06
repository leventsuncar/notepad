package com.lsuncar.notepad.db.dao.impl;

import com.lsuncar.notepad.db.dao.NoteDAO;
import com.lsuncar.notepad.db.entity.Note;
import com.lsuncar.notepad.db.repo.NoteRepository;
import com.lsuncar.notepad.dto.NoteDTO;
import com.lsuncar.notepad.dto.mapper.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Component
public class NoteDAOImpl implements NoteDAO
{

	@Autowired
	private NoteRepository noteRepository;

	private NoteMapper getMapper ()
	{
		return NoteMapper.INSTANCE;
	}

	@Override
	public List<NoteDTO> findNoteByUserId ( Long userId ) throws Exception
	{
		try
		{
			List<NoteDTO> noteDTOList = new ArrayList<>();
			List<Note> noteList = noteRepository.findByUser_IdAndActiveIsTrueOrderByUpdatedAtDesc( userId );
			if ( nonNull( noteList ) && !noteList.isEmpty() )
			{
				for ( Note note : noteList )
				{
					NoteDTO noteDTO = getMapper().toNoteDTO( note );
					noteDTOList.add( noteDTO );
				}
			}
			return noteDTOList;
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
			List<NoteDTO> noteDTOList = new ArrayList<>();
			List<Note> noteList = noteRepository.findByUser_IdAndActiveIsFalseOrderByUpdatedAtDesc( userId );
			if ( nonNull( noteList ) && !noteList.isEmpty() )
			{
				for ( Note note : noteList )
				{
					NoteDTO noteDTO = getMapper().toNoteDTO( note );
					noteDTOList.add( noteDTO );
				}
			}
			return noteDTOList;
		}
		catch ( Exception e )
		{
			throw e;
		}
	}

	@Override
	public Boolean deleteNoteById ( Long noteId ) throws Exception
	{
		Optional<Note> noteOptional = noteRepository.findById( noteId );
		if ( noteOptional.isPresent() )
		{
			Note note = noteOptional.get();
			note.setActive( false );
		}
		return null;
	}

	@Override
	public Boolean deleteNotePermanentlyById ( Long noteId ) throws Exception
	{
		return null;
	}

	@Override
	public NoteDTO save ( NoteDTO noteDTO ) throws Exception
	{
		try
		{

			return null;
		}
		catch ( Exception e )
		{
			throw e;
		}
	}
}
