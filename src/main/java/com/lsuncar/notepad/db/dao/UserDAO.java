package com.lsuncar.notepad.db.dao;

import com.lsuncar.notepad.dto.UserDTO;

public interface UserDAO {
    UserDTO findUserById(Long userId) throws Exception;

    UserDTO save(UserDTO user) throws Exception;

    UserDTO findUserByUsername(String username);

    UserDTO findUserByEmail(String email);
}
