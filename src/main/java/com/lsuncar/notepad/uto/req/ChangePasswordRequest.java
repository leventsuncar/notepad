package com.lsuncar.notepad.uto.req;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class ChangePasswordRequest {
    private Long userId;
    @Nullable
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
