package com.lsuncar.notepad.db.dao.impl;

import com.lsuncar.notepad.db.dao.NoteDAO;
import com.lsuncar.notepad.db.entity.Note;
import com.lsuncar.notepad.db.repo.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class NoteDAOImpl implements NoteDAO
{

	@Autowired
	private NoteRepository noteRepository;

	@Override
	public List<Note> findNoteByUserId ( Long userId ) throws Exception
	{
		try
		{
			List<Note> noteList = noteRepository.findByUser_IdAndActiveIsTrueOrderByUpdatedAtDesc( userId );
			return noteList;
		}
		catch ( Exception e )
		{
			throw e;
		}
	}

	@Override
	public List<Note> findDeletedNoteByUserId ( Long userId ) throws Exception
	{
		try
		{
			List<Note> noteList = noteRepository.findByUser_IdAndActiveIsFalseOrderByUpdatedAtDesc( userId );
			return noteList;
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
				save( note );
				return true;
			}
			else
			{
				return false;
			}
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
	public Note save ( Note note ) throws Exception
	{
		try
		{
			Note savedNote = noteRepository.save( note );
			return savedNote;
		}
		catch ( Exception e )
		{
			throw e;
		}
	}
}
