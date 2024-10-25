package com.lsuncar.notepad.controller;

import com.lsuncar.notepad.core.results.ErrorResult;
import com.lsuncar.notepad.core.results.SuccessDataResult;
import com.lsuncar.notepad.dto.UserDTO;
import com.lsuncar.notepad.service.UserService;
import com.lsuncar.notepad.uto.req.SendEmailForPasswordReset;
import com.lsuncar.notepad.uto.req.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("user/")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserRequest user) {
        try {
            UserDTO savedUser = userService.signup(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessDataResult<UserDTO>(savedUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult("errorSignUp"));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            UserDTO user = userService.findUserById(id);
            return ResponseEntity.ok(new SuccessDataResult<UserDTO>(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResult(e.getMessage()));
        }
    }

    @PostMapping( "/forgotPassword" )
    public ResponseEntity<?> sendResetPasswordMail(@RequestBody SendEmailForPasswordReset sendEmailForPasswordReset) {
        try {
            return ResponseEntity.ok( userService.sendEmailForPasswordReset(sendEmailForPasswordReset.getEmail() ) );
        }
        catch ( Exception e ) {
            return ResponseEntity.badRequest().body(new ErrorResult("errorSendEmail"));
        }
    }
}
