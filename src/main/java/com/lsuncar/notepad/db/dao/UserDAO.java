package com.lsuncar.notepad.db.dao;

import com.lsuncar.notepad.db.entity.User;
import com.lsuncar.notepad.dto.UserDTO;

public interface UserDAO
{
	User findUserById ( Long userId ) throws Exception;

	User save ( User user ) throws Exception;
}
