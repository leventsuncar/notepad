package com.lsuncar.notepad.db.repo;

import com.lsuncar.notepad.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	User findByIdAndActiveIsTrue ( Long userId );

}
