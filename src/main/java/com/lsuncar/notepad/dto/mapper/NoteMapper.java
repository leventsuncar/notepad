package com.lsuncar.notepad.dto.mapper;

import com.lsuncar.notepad.db.entity.Note;
import com.lsuncar.notepad.dto.NoteDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "lombok")
public interface NoteMapper {
    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

    NoteDTO toNoteDTO(Note note);

    Note toNote(NoteDTO noteDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Note partialUpdate(NoteDTO noteDTO, @MappingTarget Note note);
}
