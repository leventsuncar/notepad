package com.lsuncar.notepad.dto.mapper;

import com.lsuncar.notepad.db.entity.Note;
import com.lsuncar.notepad.dto.NoteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NoteMapper
{
	NoteMapper INSTANCE = Mappers.getMapper( NoteMapper.class );

	NoteDTO toNoteDTO ( Note note );

	Note toNote ( NoteDTO noteDTO );
}
