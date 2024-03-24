package com.lsuncar.notepad.service;

import com.lsuncar.notepad.dto.UserDTO;
import com.lsuncar.notepad.uto.req.UserRequest;

public interface UserService {
    UserDTO signup(UserRequest userRequest) throws Exception;

    UserDTO findUserById(Long userId) throws Exception;

    UserDTO findUserByUsername(String username);
}
