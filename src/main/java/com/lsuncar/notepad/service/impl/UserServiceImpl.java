package com.lsuncar.notepad.service.impl;

import com.lsuncar.notepad.Constants;
import com.lsuncar.notepad.core.exception.EntityNotFoundException;
import com.lsuncar.notepad.core.messaging.email.SMTPUtil;
import com.lsuncar.notepad.core.results.Result;
import com.lsuncar.notepad.core.results.SuccessResult;
import com.lsuncar.notepad.core.util.ResourceBundleUtil;
import com.lsuncar.notepad.db.dao.UserDAO;
import com.lsuncar.notepad.dto.UserDTO;
import com.lsuncar.notepad.service.UserService;
import com.lsuncar.notepad.uto.req.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SMTPUtil smtpUtil;

    @Override
    public UserDTO signup(UserRequest userRequest) throws Exception {
        try {

            //email and userName must be unique
            UserDTO userDTO = new UserDTO();
            userDTO.setFirstname(userRequest.getFirstname());
            userDTO.setLastname(userRequest.getLastname());
            userDTO.setUsername(userRequest.getUsername());
            userDTO.setEmail(userRequest.getEmail());
            userDTO.setCreatedAt(System.currentTimeMillis());
            userDTO.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            UserDTO savedUser = userDAO.save(userDTO);
            return savedUser;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
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
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        try {
            return userDAO.findUserByUsername(username);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Result sendEmailForPasswordReset(String email) throws Exception {
        try {
            UserDTO userDTO = userDAO.findUserByEmail(email);
            if (nonNull(userDTO)) {
                smtpUtil.sendMail(userDTO.getEmail(),
                        ResourceBundleUtil.getLocaleText("", "tr_TR"),
                        Constants.RESET_PASSWORD_MAIL);
                return new SuccessResult( "email send" );


            } else
                throw new EntityNotFoundException(""); //Emailin yanlış olduğu ile ilgili bilgi vermemek lazım!
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }
}
