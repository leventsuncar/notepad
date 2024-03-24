package com.lsuncar.notepad.db.dao.impl;

import com.lsuncar.notepad.db.dao.CompanyDAO;
import com.lsuncar.notepad.db.entity.Company;
import com.lsuncar.notepad.db.repo.CompanyRepository;
import com.lsuncar.notepad.dto.CompanyDTO;
import com.lsuncar.notepad.dto.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CompanyDAOImpl implements CompanyDAO {

    @Autowired
    private CompanyRepository companyRepository;

    private CompanyMapper getMapper() {
        return CompanyMapper.INSTANCE;
    }

    @Override
    public CompanyDTO findCompanyById(Long companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        try {
            if (company.isPresent())
                return getMapper().toCompanyDTO(company.get());
            return null;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public CompanyDTO findCompanyByHostName(String hostName) {
        try {
            Company company = companyRepository.findByHostName(hostName);
            return getMapper().toCompanyDTO(company);
        } catch (Exception e) {
            throw e;
        }
    }
}
