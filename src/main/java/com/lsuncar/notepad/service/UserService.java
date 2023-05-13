package com.lsuncar.notepad.service;

import com.lsuncar.notepad.dto.UserDTO;

public interface UserService
{
	UserDTO signup ( UserDTO userDTO ) throws Exception;

	UserDTO findUserById ( Long userId ) throws Exception;

	UserDTO findUserByUsername ( String username );
}
