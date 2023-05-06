package com.lsuncar.notepad.db.dao;

import com.lsuncar.notepad.common.exception.EntityNotFoundException;
import com.lsuncar.notepad.dto.UserDTO;

public interface UserDAO
{
	UserDTO findUserById ( Long userId ) throws Exception;

	UserDTO save ( UserDTO userDTO ) throws Exception;
}
