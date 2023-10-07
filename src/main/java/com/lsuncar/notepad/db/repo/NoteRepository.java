package com.lsuncar.notepad.db.repo;

import com.lsuncar.notepad.db.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long>
{
	List<Note> findNotesByOwner_Id ( Long userId );

//	List<Note> findByUser_IdAndActiveIsTrueOrderByUpdatedAtDesc ( Long userId );

}
