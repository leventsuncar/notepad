package com.lsuncar.notepad.dto.mapper;

import com.lsuncar.notepad.db.entity.CompanyUser;
import com.lsuncar.notepad.dto.CompanyUserDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "lombok", uses = {UserMapper.class, CompanyMapper.class})
public interface CompanyUserMapper {
    CompanyUserMapper INSTANCE = Mappers.getMapper(CompanyUserMapper.class);

    CompanyUser toEntity(CompanyUserDTO companyUserDto);

    CompanyUserDTO toDto(CompanyUser companyUser);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CompanyUser partialUpdate(CompanyUserDTO companyUserDto, @MappingTarget CompanyUser companyUser);
}
