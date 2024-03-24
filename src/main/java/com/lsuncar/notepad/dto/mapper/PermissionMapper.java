package com.lsuncar.notepad.dto.mapper;

import com.lsuncar.notepad.db.entity.Permission;
import com.lsuncar.notepad.dto.PermissionDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "lombok", uses = {UserMapper.class, CompanyMapper.class, NoteMapper.class})
public interface PermissionMapper {

    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    Permission toEntity(PermissionDTO permissionDTO);

    PermissionDTO toDto(Permission permission);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Permission partialUpdate(PermissionDTO permissionDTO, @MappingTarget Permission permission);
}
