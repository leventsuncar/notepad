package com.lsuncar.notepad;

import com.lsuncar.notepad.db.dao.NoteDAO;
import com.lsuncar.notepad.db.dao.UserDAO;
import com.lsuncar.notepad.dto.NoteDTO;
import com.lsuncar.notepad.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class TestDataInitializer implements ApplicationRunner {

    private static final Logger logger = Logger.getLogger(TestDataInitializer.class.getName());

    private final UserDAO userDAO;
    private final NoteDAO noteDAO;
    private final PasswordEncoder passwordEncoder;
    @Value("${test.mode.init.test.data}")
    private String initTestData;

    public TestDataInitializer(UserDAO userDAO, NoteDAO noteDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.noteDAO = noteDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (Boolean.parseBoolean(initTestData)) {
            for (int i = 0; i < 10; i++) {
                UserDTO userDTO = new UserDTO();
                userDTO.setUsername("test" + i);
                userDTO.setPassword(passwordEncoder.encode("test" + i));
                userDTO.setEmail("test" + i + "@mail.com");
                userDTO.setFirstname("test" + i);
                userDTO.setLastname("test" + i);
                userDTO.setCreatedAt(System.currentTimeMillis());
                UserDTO savedUser = userDAO.save(userDTO);
                logger.info("User created: " + userDTO.getUsername());

                NoteDTO noteDTO = new NoteDTO();
                noteDTO.setContent("lorem ipsum dolor sit amet");
                noteDTO.setTitle("Title" + i);
                noteDTO.setActive(true);
                noteDTO.setOwner(savedUser);
                noteDTO.setCreatedAt(System.currentTimeMillis());
                noteDAO.save(noteDTO);
            }
        }
    }
}
