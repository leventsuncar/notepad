package com.lsuncar.notepad.db.dao.impl;

import com.lsuncar.notepad.db.dao.UserDAO;
import com.lsuncar.notepad.db.entity.User;
import com.lsuncar.notepad.db.repo.UserRepository;
import com.lsuncar.notepad.dto.UserDTO;
import com.lsuncar.notepad.dto.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static java.util.Objects.nonNull;

@Component
public class UserDAOImpl implements UserDAO {

    @Autowired
    private UserRepository userRepository;

    private UserMapper getMapper() {
        return UserMapper.INSTANCE;
    }

    @Override
    public UserDTO findUserById(Long userId) {
        try {
            User user = userRepository.findByIdAndActiveIsTrue(userId);
            if (nonNull(user)) return getMapper().toUserDTO(user);
            else return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        try {
            User user = getMapper().toUser(userDTO);
            user.setActive(true);
            user.setCreatedAt(new Date().getTime());
            User savedUser = userRepository.save(user);
            return getMapper().toUserDTO(savedUser);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        try {
            User user = userRepository.findUserByUsername(username);
            UserDTO userDTO = getMapper().toUserDTO(user);
            return userDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        try {
            User user = userRepository.findUserByEmailAndActiveIsTrue(email);
            UserDTO userDTO = getMapper().toUserDTO(user);
            return userDTO;
        } catch (Exception e) {
            throw e;
        }
    }
}
