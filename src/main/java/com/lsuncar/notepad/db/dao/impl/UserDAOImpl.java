package com.lsuncar.notepad.db.dao.impl;

import com.lsuncar.notepad.db.dao.UserDAO;
import com.lsuncar.notepad.db.entity.User;
import com.lsuncar.notepad.db.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAOImpl implements UserDAO
{

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUserById ( Long userId ) throws Exception
	{
		try
		{
			User user = userRepository.findByIdAndActiveIsTrue( userId );
			return user;
		}
		catch ( Exception e )  //TODO Custom Exception
		{
			throw e;
		}
	}

	@Override
	public User save ( User user ) throws Exception
	{
		try
		{
			user.setActive( true );
			user.setCreatedAt( System.currentTimeMillis() );
			user.setUpdatedAt( System.currentTimeMillis() );
			User savedUser = userRepository.save( user );
			return savedUser;
		}
		catch ( Exception e )
		{
			throw e;
		}
	}
}
