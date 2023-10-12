package com.lsuncar.notepad.dto.mapper;

import com.lsuncar.notepad.db.entity.UserSharedNote;
import com.lsuncar.notepad.dto.UserSharedNotesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "lombok", uses = {UserMapper.class, NoteMapper.class})
public interface UserSharedNotesMapper {

    UserSharedNotesMapper INSTANCE = Mappers.getMapper(UserSharedNotesMapper.class);

    UserSharedNote toEntity(UserSharedNotesDTO userSharedNotesDTO);

    UserSharedNotesDTO toDto(UserSharedNote userSharedNote);

}
