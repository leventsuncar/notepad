package com.lsuncar.notepad.db.dao;

import com.lsuncar.notepad.dto.UserSharedNotesDTO;

import java.util.List;

public interface UserSharedNoteDAO {

    List<UserSharedNotesDTO> findAllUserSharedNotesByUserId(Long userId) throws Exception;

    List<UserSharedNotesDTO> findAllUserSharedNotesByNoteId(Long userId) throws Exception;

    UserSharedNotesDTO add(UserSharedNotesDTO userSharedNotes) throws Exception;

    void deleteUserSharedNote(UserSharedNotesDTO userSharedNotesDTO) throws Exception;

    void deletePermanentlyUserSharedNote(UserSharedNotesDTO userSharedNotes) throws Exception;

}
