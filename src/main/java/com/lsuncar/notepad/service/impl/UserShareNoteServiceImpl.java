package com.lsuncar.notepad.service.impl;

import com.lsuncar.notepad.controller.req.ShareNoteRequest;
import com.lsuncar.notepad.db.dao.NoteDAO;
import com.lsuncar.notepad.db.dao.UserDAO;
import com.lsuncar.notepad.db.dao.UserSharedNoteDAO;
import com.lsuncar.notepad.dto.NoteDTO;
import com.lsuncar.notepad.dto.UserDTO;
import com.lsuncar.notepad.dto.UserSharedNotesDTO;
import com.lsuncar.notepad.service.UserSharedNotesService;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserShareNoteServiceImpl implements UserSharedNotesService {

    @Autowired
    private UserSharedNoteDAO userSharedNoteDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private NoteDAO noteDAO;

    @Autowired
    private ProducerTemplate producerTemplate;

    @Value("${rabbit.active}")
    private boolean rabbitActive;

    @Override
    public List<UserSharedNotesDTO> findAllUserSharedNotesByUserId(Long userId) throws Exception {
        try {
            List<UserSharedNotesDTO> userSharedNotesDTO = userSharedNoteDAO.findAllUserSharedNotesByUserId(userId);
            return userSharedNotesDTO;
        } catch (Exception e) {
            throw e; // Custom exception
        }
    }

    @Override
    public List<UserSharedNotesDTO> findAllUserSharedNotesByNoteId(Long noteId) throws Exception {
        try {
            List<UserSharedNotesDTO> userSharedNotesDTO = userSharedNoteDAO.findAllUserSharedNotesByNoteId(noteId);
            return userSharedNotesDTO;
        } catch (Exception e) {
            throw e; // Custom exception
        }
    }

    @Override
    public UserSharedNotesDTO add(ShareNoteRequest shareNoteRequest) throws Exception {
        try {
            UserSharedNotesDTO oneOfAdded = null;
            NoteDTO noteDTO = noteDAO.findNoteById(shareNoteRequest.getNoteId());
            if (Objects.nonNull(noteDTO)) {
                for (Long userId : shareNoteRequest.getSharingUsers()) {
                    UserDTO userDTO = userDAO.findUserById(userId);
                    if (Objects.nonNull(userDTO)) {
                        UserSharedNotesDTO adding = new UserSharedNotesDTO();
                        adding.setUser(userDTO);
                        adding.setNote(noteDTO);
                        UserSharedNotesDTO added = userSharedNoteDAO.add(adding);
                        oneOfAdded = added;
                        if (rabbitActive)
                            producerTemplate.sendBody("direct:shareNote", added);
                    }
                }
            }
            return oneOfAdded;
        } catch (Exception e) {
            throw e; // Custom exception
        }
    }

    @Override
    public void deletePermanentlyUserSharedNote(UserSharedNotesDTO userSharedNotes) throws Exception {
        try {
            userSharedNoteDAO.deletePermanentlyUserSharedNote(userSharedNotes);
        } catch (Exception e) {
            throw e;
        }
    }
}
