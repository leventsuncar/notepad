package com.lsuncar.notepad.db.repo;

import com.lsuncar.notepad.db.entity.CompanyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyUserRepository extends JpaRepository<CompanyUser, Long> {
    List<CompanyUser> findAllByCompanyId(Long companyId);

    CompanyUser findByUserId(Long userId);

}
