package com.lsuncar.notepad.core.security.service;

import com.lsuncar.notepad.core.security.JwtUtils;
import com.lsuncar.notepad.core.security.controller.req.LoginRequest;
import com.lsuncar.notepad.core.security.controller.response.LoginResponse;
import com.lsuncar.notepad.db.entity.User;
import com.lsuncar.notepad.db.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginResponse signIn(LoginRequest loginRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()
                )
        );
        User user = userRepository.findUserByUsername( loginRequest.getUsername() );
        String token = jwtUtils.generateToken( new HashMap<>(), user );
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken( token );
        loginResponse.setUserId( user.getId() );
        return loginResponse;
    }
}
