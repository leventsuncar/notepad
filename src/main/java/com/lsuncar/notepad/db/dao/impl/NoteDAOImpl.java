package com.lsuncar.notepad.db.dao.impl;

import com.lsuncar.notepad.db.dao.NoteDAO;
import com.lsuncar.notepad.db.entity.Note;
import com.lsuncar.notepad.db.repo.NoteRepository;
import com.lsuncar.notepad.dto.NoteDTO;
import com.lsuncar.notepad.dto.mapper.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
			List<Note> noteList = noteRepository.findByUser_IdAndActiveIsTrueOrderByUpdatedAtDesc( userId );
			List<NoteDTO> noteDTOList = new ArrayList<>();
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
			List<Note> noteList = noteRepository.findByUser_IdAndActiveIsFalseOrderByUpdatedAtDesc( userId );
			List<NoteDTO> noteDTOList = new ArrayList<>();
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
		try
		{
			Optional<Note> noteOptional = noteRepository.findById( noteId );
			if ( noteOptional.isPresent() )
			{
				Note note = noteOptional.get();
				note.setActive( false );
				note.setUpdatedAt( System.currentTimeMillis() );
				NoteDTO noteDTO = getMapper().toNoteDTO( note );
				save( noteDTO );
				return true;
			}
			else
				return false;
		}
		catch ( Exception e )
		{
			throw e;
		}

	}

	@Override
	@Transactional
	public Boolean deleteNotePermanentlyById ( Long noteId ) throws Exception
	{
		try
		{
			noteRepository.deleteById( noteId );
			return true;
		}
		catch ( Exception e )
		{
			throw e;
		}

	}

	@Override
	public NoteDTO save ( NoteDTO noteDTO ) throws Exception
	{
		try
		{
			Note note = getMapper().toNote( noteDTO );
			note.setActive( true );
			note.setUpdatedAt( System.currentTimeMillis() );
			note.setCreatedAt( System.currentTimeMillis() );
			Note savedNote = noteRepository.save( note );
			NoteDTO savedNoteDTO = getMapper().toNoteDTO( savedNote );
			return savedNoteDTO;
		}
		catch ( Exception e )
		{
			throw e;
		}
	}

	@Override
	public NoteDTO findNoteById ( Long noteId ) throws Exception
	{
		try
		{
			Optional<Note> note = noteRepository.findById( noteId );
			if ( note.isPresent() )
			{
				NoteDTO noteDTO = getMapper().toNoteDTO( note.get() );
				return noteDTO;
			}
			else
				return null;
		}
		catch ( Exception e )
		{
			throw e;
		}
	}
}
