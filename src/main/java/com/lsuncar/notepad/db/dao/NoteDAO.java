package com.lsuncar.notepad.db.dao;

import com.lsuncar.notepad.dto.NoteDTO;

import java.util.List;

public interface NoteDAO
{
	List<NoteDTO> findNoteByUserId ( Long userId ) throws Exception;

	List<NoteDTO> findDeletedNoteByUserId ( Long userId ) throws Exception;

	Boolean deleteNoteById ( Long noteId ) throws Exception;

	Boolean deleteNotePermanentlyById ( Long noteId ) throws Exception;

	NoteDTO save ( NoteDTO noteDTO ) throws Exception;
}
