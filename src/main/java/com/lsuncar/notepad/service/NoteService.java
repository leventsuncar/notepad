package com.lsuncar.notepad.service;

import com.lsuncar.notepad.controller.req.NoteRequest;
import com.lsuncar.notepad.controller.req.ShareNoteRequest;
import com.lsuncar.notepad.dto.NoteDTO;

import java.util.List;

public interface NoteService {
    List<NoteDTO> findNoteByUserId(Long userId) throws Exception;

    List<NoteDTO> findDeletedNoteByUserId(Long userId) throws Exception;

    Boolean deleteNoteById(Long noteId) throws Exception;

    Boolean deleteNotePermanentlyById(Long noteId) throws Exception;

    NoteDTO save(NoteRequest note) throws Exception;

    NoteDTO shareNote(ShareNoteRequest shareNoteRequest) throws Exception;
}
