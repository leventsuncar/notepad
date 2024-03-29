package com.lsuncar.notepad.service.impl;

import com.lsuncar.notepad.core.exception.EntityNotFoundException;
import com.lsuncar.notepad.db.dao.UserDAO;
import com.lsuncar.notepad.dto.UserDTO;
import com.lsuncar.notepad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDTO signup(UserDTO userDTO) throws Exception {
        try {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            UserDTO savedUser = userDAO.save(userDTO);
            return savedUser;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public UserDTO findUserById(Long userId) throws Exception {
        try {
            UserDTO user = userDAO.findUserById(userId);
            if (nonNull(user))
                return user;
            else
                throw new EntityNotFoundException("Not found");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        try {
            return userDAO.findUserByUsername(username);
        } catch (Exception e) {
            throw e;
        }
    }
}
