package com.lsuncar.notepad.core.security.controller;

import com.lsuncar.notepad.core.security.controller.req.LoginRequest;
import com.lsuncar.notepad.core.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        try {
            return ResponseEntity.ok(authenticationService.signIn(loginRequest));
        } catch (Exception e) {
            throw e;
        }
    }


}
