package com.lsuncar.notepad.service;

import com.lsuncar.notepad.controller.req.ShareNoteRequest;
import com.lsuncar.notepad.dto.UserSharedNotesDTO;

import java.util.List;

public interface UserSharedNotesService {

    List<UserSharedNotesDTO> findAllUserSharedNotesByUserId(Long userId) throws Exception;

    List<UserSharedNotesDTO> findAllUserSharedNotesByNoteId(Long userId) throws Exception;

    UserSharedNotesDTO add(ShareNoteRequest shareNoteRequest) throws Exception;

    void deletePermanentlyUserSharedNote(UserSharedNotesDTO userSharedNotes) throws Exception;

}
