package com.lsuncar.notepad.dto.mapper;

import com.lsuncar.notepad.db.entity.User;
import com.lsuncar.notepad.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "lombok")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);

}
