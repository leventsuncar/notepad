package com.lsuncar.notepad.uto.req;

import lombok.Data;

@Data
public class PermissionRequest {
    private UserPermission userPermissions;
    private CompanyPermission companyPermissions;
}
