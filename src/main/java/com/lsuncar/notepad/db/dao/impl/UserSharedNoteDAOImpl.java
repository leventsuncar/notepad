package com.lsuncar.notepad.db.dao.impl;

import com.lsuncar.notepad.db.dao.UserSharedNoteDAO;
import com.lsuncar.notepad.db.entity.UserSharedNote;
import com.lsuncar.notepad.db.repo.UserSharedNoteRepository;
import com.lsuncar.notepad.dto.UserSharedNotesDTO;
import com.lsuncar.notepad.dto.mapper.UserSharedNotesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


            }


        } catch (Exception e) {

        }

        return null;

    }

    @Override
    public List<UserSharedNotesDTO> findAllUserSharedNotesByNoteId(Long userId) {
        return null;
    }

    @Override
    public UserSharedNotesDTO add(UserSharedNotesDTO userSharedNotes) {
        return null;
    }

    @Override
    public void deleteUserSharedNote(UserSharedNotesDTO userSharedNotesDTO) throws Exception {

    }

    @Override
    public void deletePermanentlyUserSharedNote(UserSharedNotesDTO userSharedNotes) throws Exception {

    }
}
