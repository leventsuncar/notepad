package com.lsuncar.notepad.service.impl;

import com.lsuncar.notepad.controller.req.NoteRequest;
import com.lsuncar.notepad.core.exception.EntityNotFoundException;
import com.lsuncar.notepad.db.dao.NoteDAO;
import com.lsuncar.notepad.db.dao.UserDAO;
import com.lsuncar.notepad.dto.NoteDTO;
import com.lsuncar.notepad.dto.UserDTO;
import com.lsuncar.notepad.dto.mapper.NoteMapper;
import com.lsuncar.notepad.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteDAO noteDAO;

    @Autowired
    private UserDAO userDAO;

    private NoteMapper getMapper() {
        return NoteMapper.INSTANCE;
    }

    private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);

    @Override
    public List<NoteDTO> findNoteByUserId(Long userId) throws Exception {
        try {
            List<NoteDTO> notes = noteDAO.findNoteByUserId(userId);
            if (nonNull(notes) && !notes.isEmpty())
                return notes;
            else
                throw new EntityNotFoundException("Notes not found");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<NoteDTO> findDeletedNoteByUserId(Long userId) throws Exception {
        try {
            List<NoteDTO> notes = noteDAO.findDeletedNoteByUserId(userId);
            if (nonNull(notes) && !notes.isEmpty())
                return notes;
            else
                throw new EntityNotFoundException("Notes not found");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean deleteNoteById(Long noteId) throws Exception {
        try {
            NoteDTO noteDTO = noteDAO.findNoteById(noteId);
            if (nonNull(noteDTO)) {
                noteDAO.deleteNoteById(noteId);
                return true;
            } else
                throw new EntityNotFoundException("Note not found");
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public Boolean deleteNotePermanentlyById(Long noteId) throws Exception {
        try {
            NoteDTO noteDTO = noteDAO.findNoteById(noteId);
            if (nonNull(noteDTO)) {
                noteDAO.deleteNotePermanentlyById(noteId);
                return true;
            } else
                throw new EntityNotFoundException("Note not found");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public NoteDTO save(NoteRequest note) throws Exception {
        try {
            UserDTO user = userDAO.findUserById(note.getUserId());
            if (isNull(user)) {
                throw new EntityNotFoundException("User not found");
            }
            NoteDTO noteDTO = new NoteDTO();
            noteDTO.setContent(note.getContent());
            noteDTO.setTitle(note.getTitle());
            noteDTO.setOwner(user);
            NoteDTO savedNote = noteDAO.save(noteDTO);
            return savedNote;
        } catch (Exception e) {
            throw e;
        }
    }

//    @Override  TODO
//    public NoteEntityDTO shareNote(ShareNoteRequest shareNoteRequest) throws Exception {
//        try {
//            NoteEntityDTO noteDTO = noteDAO.findNoteById(shareNoteRequest.getNoteId());
//            if (Objects.nonNull(noteDTO)) //Note exist
//            {
//                List<UserEntityDTO> addingUsers = new ArrayList<>();
//                if (Objects.nonNull(shareNoteRequest.getSharingUsers()) && !shareNoteRequest.getSharingUsers().isEmpty()) {
//                    for (Long userId : shareNoteRequest.getSharingUsers()) {
//                        try {
//                            UserEntityDTO userDTO = userDAO.findUserById(userId);
//                            if (nonNull(userDTO)) {
//                                addingUsers.add(userDTO);
//                            }
//                        } catch (Exception e) {
//                            logger.warn("Selected User Not Found:" + userId);
//                        }
//                    }
//                } else throw new Exception("User Ids cannot be EMPTY");
//
//                noteDTO.getSharedUsers().addAll(addingUsers);
//                return noteDAO.save(noteDTO);
//            } else throw new EntityNotFoundException("Selected Note Not Found!");
//        } catch (Exception e) {
//            throw e;
//        }
//    }

    @Override
    public List<NoteDTO> getAllUserNotes(Long userId) throws Exception {
        try {
            List<NoteDTO> noteDTOList = noteDAO.findAllNotesByUser(userId);
            List<NoteDTO> responseNotes = new ArrayList<>();
            if (nonNull(noteDTOList) && !noteDTOList.isEmpty()) {
                for (NoteDTO noteDTO : noteDTOList) {

                }
                return responseNotes;
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }
}
