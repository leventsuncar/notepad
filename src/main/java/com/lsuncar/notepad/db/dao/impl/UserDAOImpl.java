package com.lsuncar.notepad.db.dao.impl;

import com.lsuncar.notepad.common.exception.EntityNotFoundException;
import com.lsuncar.notepad.db.dao.UserDAO;
import com.lsuncar.notepad.db.entity.User;
import com.lsuncar.notepad.db.repo.UserRepository;
import com.lsuncar.notepad.dto.UserDTO;
import com.lsuncar.notepad.dto.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAOImpl implements UserDAO
{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO findUserById ( Long userId ) throws Exception
	{
		try
		{
			User user = userRepository.findByIdAndActiveIsTrue( userId );
			UserDTO userDTO = UserMapper.INSTANCE.toUserDTO( user );
			return userDTO;
		}
		catch ( Exception e )  //TODO Custom Exception
		{
			throw e;
		}
	}

	@Override
	public UserDTO save ( UserDTO userDTO ) throws Exception
	{
		try
		{
			User user = UserMapper.INSTANCE.toUser( userDTO );
			User savedUser = userRepository.save( user );
			UserDTO savedUserDTO = UserMapper.INSTANCE.toUserDTO( savedUser );
			return savedUserDTO;
		}
		catch ( Exception e )
		{
			throw e;
		}
	}
}
