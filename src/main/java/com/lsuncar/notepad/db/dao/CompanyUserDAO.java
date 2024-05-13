package com.lsuncar.notepad.db.dao;

import com.lsuncar.notepad.dto.CompanyUserDTO;

import java.util.List;

public interface CompanyUserDAO {
    List<CompanyUserDTO> findAllByCompanyId(Long companyId);

    CompanyUserDTO findByUserId(Long userId);
}
