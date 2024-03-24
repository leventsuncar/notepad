package com.lsuncar.notepad.dto;

import com.lsuncar.notepad.db.entity.CompanyUser;
import lombok.Data;

/**
 * DTO for {@link CompanyUser}
 */
@Data
public class CompanyUserDTO extends BaseDTO {
    private final Long id;
    private final UserDTO user;
    private final CompanyDTO company;
}