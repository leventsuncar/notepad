package com.lsuncar.notepad.db.dao.impl;

import com.lsuncar.notepad.db.dao.UserSharedNoteDAO;
import com.lsuncar.notepad.db.entity.UserSharedNote;
import com.lsuncar.notepad.db.repo.UserSharedNoteRepository;
import com.lsuncar.notepad.dto.UserSharedNotesDTO;
import com.lsuncar.notepad.dto.mapper.UserSharedNotesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserSharedNoteDAOImpl implements UserSharedNoteDAO {

    @Autowired
    private UserSharedNoteRepository userSharedNoteRepository;

    private UserSharedNotesMapper getMapper() {
        return UserSharedNotesMapper.INSTANCE;
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public List<UserSharedNotesDTO> findAllUserSharedNotesByUserId(Long userId) {

        try {
            List<UserSharedNote> userSharedNoteList = userSharedNoteRepository.findAllByUser_Id(userId);

            if (Objects.nonNull(userSharedNoteList) && !userSharedNoteList.isEmpty()) {
                List<UserSharedNotesDTO> userSharedNotesDTOS = new ArrayList<>();

                for (UserSharedNote userSharedNote : userSharedNoteList) {
                    UserSharedNotesDTO userSharedNotesDTO = getMapper().toDto(userSharedNote);
                    userSharedNotesDTOS.add(userSharedNotesDTO);
                }
                return userSharedNotesDTOS;
            } else return null;
        } catch (Exception e) {
            throw e;
        }
    }

    //Buna gerek var mı?

    /**
     * @param noteId
     * @return
     */
    @Override
    public List<UserSharedNotesDTO> findAllUserSharedNotesByNoteId(Long noteId) {

        try {
            List<UserSharedNote> userSharedNoteList = userSharedNoteRepository.findAllByNote_Id(noteId);

            if (Objects.nonNull(userSharedNoteList) && !userSharedNoteList.isEmpty()) {
                List<UserSharedNotesDTO> userSharedNotesDTOS = new ArrayList<>();

                for (UserSharedNote userSharedNote : userSharedNoteList) {
                    UserSharedNotesDTO userSharedNotesDTO = getMapper().toDto(userSharedNote);
                    userSharedNotesDTOS.add(userSharedNotesDTO);
                }
                return userSharedNotesDTOS;
            } else return null;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param userSharedNoteDTO
     * @return
     */
    @Override
    public UserSharedNotesDTO add(UserSharedNotesDTO userSharedNoteDTO) {

        try {
            UserSharedNote userSharedNote = getMapper().toEntity(userSharedNoteDTO);
            UserSharedNote savedUserSharedNote = userSharedNoteRepository.save(userSharedNote);
            UserSharedNotesDTO savedUserSharedNotesDTO = getMapper().toDto(savedUserSharedNote);
            return savedUserSharedNotesDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param userSharedNotes
     * @throws Exception
     */
    @Override
    @Transactional
    public void deletePermanentlyUserSharedNote(UserSharedNotesDTO userSharedNotes) throws Exception {
        try {
            UserSharedNote userSharedNote = getMapper().toEntity(userSharedNotes);
            userSharedNoteRepository.delete(userSharedNote);
        } catch (Exception e) {
            throw e;
        }
    }
}
