package com.lsuncar.notepad.dto;

import com.lsuncar.notepad.db.entity.Company;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link Company}
 */
@Data
public class CompanyDTO extends BaseDTO {

    private final Long id;
    private final String name;
    private final String hostName;
}