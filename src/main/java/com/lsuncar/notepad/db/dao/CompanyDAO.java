package com.lsuncar.notepad.db.dao;

import com.lsuncar.notepad.dto.CompanyDTO;

public interface CompanyDAO {
    CompanyDTO findCompanyById ( Long companyId );
    CompanyDTO findCompanyByHostName ( String hostName );
}
