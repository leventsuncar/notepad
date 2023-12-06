package com.lsuncar.notepad.dto.mapper;

import com.lsuncar.notepad.db.entity.Company;
import com.lsuncar.notepad.dto.CompanyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper ( componentModel = "lombok")
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper( CompanyMapper.class );

    Company toCompany (CompanyDTO companyDTO);
    CompanyDTO toCompanyDTO ( Company company );

}
