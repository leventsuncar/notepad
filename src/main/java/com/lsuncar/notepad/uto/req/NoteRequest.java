package com.lsuncar.notepad.uto.req;

import lombok.Data;

import java.util.List;

@Data
public class NoteRequest {
    private String title;
    private String content;
    private Long userId;  //Creator user
    private List<CompanyPermission> companyPermissions;
    private List<UserPermission> userPermissions;
}
