package com.lsuncar.notepad.db.repo;

import com.lsuncar.notepad.db.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByHostName(String hostName);
}
