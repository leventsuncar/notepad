package com.lsuncar.notepad.db.dao.impl;

import com.lsuncar.notepad.db.dao.CompanyUserDAO;
import com.lsuncar.notepad.db.entity.CompanyUser;
import com.lsuncar.notepad.db.repo.CompanyUserRepository;
import com.lsuncar.notepad.dto.CompanyUserDTO;
import com.lsuncar.notepad.dto.mapper.CompanyUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Component
public class CompanyUserDAOImpl implements CompanyUserDAO {

    @Autowired
    private CompanyUserRepository companyUserRepository;

    private CompanyUserMapper getMapper() {
        return CompanyUserMapper.INSTANCE;
    }

    @Override
    public List<CompanyUserDTO> findAllByCompanyId(Long companyId) {
        try {
            List<CompanyUser> companyUsers = companyUserRepository.findAllByCompanyId(companyId);
            List<CompanyUserDTO> companyUserDTOList = new ArrayList<>();
            if (nonNull(companyUsers) && !companyUsers.isEmpty()) {
                for (CompanyUser user : companyUsers)
                    companyUserDTOList.add(getMapper().toDto(user));
            }
            return companyUserDTOList;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public CompanyUserDTO findByUserId(Long userId) {
        try {
            CompanyUser companyUser = companyUserRepository.findByUserId(userId);
            if (nonNull(companyUser)) {
                return getMapper().toDto(companyUser);
            } else
                return null;

        } catch (Exception e) {
            throw e;
        }
    }
}
