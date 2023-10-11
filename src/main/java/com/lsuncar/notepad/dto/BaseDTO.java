package com.lsuncar.notepad.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDTO implements Serializable {
    private Long createdAt;
    private Long updatedAt;
    private Boolean active;
}
