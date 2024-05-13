package com.lsuncar.notepad.uto.req;

import com.lsuncar.notepad.enumeration.AccessLevel;
import lombok.Data;

@Data
public class UserPermission {
    private String emailAddress;
    private AccessLevel accessLevel;
}
