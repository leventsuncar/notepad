package com.lsuncar.notepad.uto.req;

import com.lsuncar.notepad.enumeration.AccessLevel;
import lombok.Data;

@Data
public class CompanyPermission {
    private Long companyId;
    private AccessLevel accessLevel;
}
