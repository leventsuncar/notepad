package com.lsuncar.notepad.db.dao;

import com.lsuncar.notepad.db.entity.Note;

import java.util.List;

public interface NoteDAO
{
	List<Note> findNoteByUserId ( Long userId ) throws Exception;

	List<Note> findDeletedNoteByUserId ( Long userId ) throws Exception;

	Boolean deleteNoteById ( Long noteId ) throws Exception;

	Boolean deleteNotePermanentlyById ( Long noteId ) throws Exception;

	Note save ( Note note ) throws Exception;
}
