package com.lsuncar.notepad.service.impl;

import com.lsuncar.notepad.core.exception.EntityNotFoundException;
import com.lsuncar.notepad.db.dao.UserDAO;
import com.lsuncar.notepad.db.entity.User;
import com.lsuncar.notepad.dto.UserDTO;
import com.lsuncar.notepad.dto.mapper.UserMapper;
import com.lsuncar.notepad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserDAO userDAO;

	private UserMapper getMapper()
	{
		return UserMapper.INSTANCE;
	}

	@Override
	public UserDTO signup ( UserDTO userDTO ) throws Exception
	{
		try
		{
			//TODO kontroller
			User user = getMapper().toUser( userDTO );
			User savedUser = userDAO.save( user );
			UserDTO savedUserDTO = getMapper().toUserDTO( savedUser );
			return savedUserDTO;
		}
		catch ( Exception e )
		{
			throw e;
		}
	}

	@Override
	public UserDTO findUserById ( Long userId ) throws Exception
	{
		try
		{
			User user = userDAO.findUserById( userId );
			if ( nonNull( user ) )
			{
				UserDTO userDTO = getMapper().toUserDTO( user );
				return userDTO;
			}
			else
				throw new EntityNotFoundException( "Not found" );
		}
		catch ( Exception e )
		{
			throw e;
		}

	}
}
