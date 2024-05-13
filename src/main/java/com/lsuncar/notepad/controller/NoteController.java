package com.lsuncar.notepad.controller;

import com.lsuncar.notepad.core.results.ErrorResult;
import com.lsuncar.notepad.core.results.SuccessDataResult;
import com.lsuncar.notepad.core.results.SuccessResult;
import com.lsuncar.notepad.dto.NoteDTO;
import com.lsuncar.notepad.service.NoteService;
import com.lsuncar.notepad.uto.req.NoteRequest;
import com.lsuncar.notepad.uto.req.ShareNoteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("note/")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("add")
    public ResponseEntity<?> addNote(@RequestBody NoteRequest noteDTO) {
        try {
            NoteDTO savedNote = noteService.save(noteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessDataResult<NoteDTO>(savedNote, "note.noteAdded"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResult("note.noteCouldNotBeAdded"));
        }
    }

    @GetMapping("get/{userId}")
    public ResponseEntity<?> getNotes(@PathVariable Long userId) {
        try {
            List<NoteDTO> noteDTOList = noteService.findNoteByUserId(userId);
            return ResponseEntity.ok(new SuccessDataResult<List<NoteDTO>>(noteDTOList, "note.notesCouldBeFound"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResult("note.notesNotFound"));
        }
    }

    @GetMapping("get-deleted/{userId}")
    public ResponseEntity<?> getDeletedNotes(@PathVariable Long userId) {
        try {
            List<NoteDTO> noteDTOList = noteService.findDeletedNoteByUserId(userId);
            return ResponseEntity.ok(new SuccessDataResult<List<NoteDTO>>(noteDTOList, "note.deletedNotesFound"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResult("note.notesCouldNotBeDeleted"));
        }
    }

    @GetMapping("get/all/{userId}")
    public ResponseEntity<?> getAllByUser(@PathVariable Long userId) {

        try {
            List<NoteDTO> noteResponses = noteService.getAllUserNotes(userId);
            return ResponseEntity.ok(new SuccessDataResult<List<NoteDTO>>(noteResponses, "note.notesCouldBeFound"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResult("note.notesNotFound"));
        }
    }

    @DeleteMapping("delete/{noteId}")
    public ResponseEntity<?> deleteNoteById(@PathVariable Long noteId) {
        try {
            noteService.deleteNoteById(noteId);
            return ResponseEntity.ok(new SuccessResult("note.noteDeleted"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("share")
    public ResponseEntity<?> shareNote(@RequestBody ShareNoteRequest shareNoteRequest) {
        try {
            noteService.shareNote(shareNoteRequest);
            return ResponseEntity.ok(new SuccessResult("note.noteShareSuccess"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
