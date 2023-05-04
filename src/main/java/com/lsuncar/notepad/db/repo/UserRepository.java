package com.lsuncar.notepad.db.repo;

import com.lsuncar.notepad.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
	User findByIdAndActiveIsTrue ( Long userId );

}
