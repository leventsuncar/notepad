package com.lsuncar.notepad.controller;

import com.lsuncar.notepad.controller.req.NoteRequest;
import com.lsuncar.notepad.controller.req.ShareNoteRequest;
import com.lsuncar.notepad.dto.NoteDTO;
import com.lsuncar.notepad.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("note/")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping("add")
    public ResponseEntity<?> addNote(@RequestBody NoteRequest noteDTO) {
        try {
            NoteDTO savedNote = noteService.save(noteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedNote);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("get/{userId}")
    public ResponseEntity<?> getNotes(@PathVariable Long userId) {
        try {
            List<NoteDTO> noteDTOList = noteService.findNoteByUserId(userId);
            return ResponseEntity.ok(noteDTOList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("get-deleted/{userId}")
    public ResponseEntity<?> getDeletedNotes(@PathVariable Long userId) {
        try {
            List<NoteDTO> noteDTOList = noteService.findDeletedNoteByUserId(userId);
            return ResponseEntity.ok(noteDTOList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("get/all/{userId}")
    public ResponseEntity<?> getAllByUser(@PathVariable Long userId) {

        try {
            List<NoteDTO> noteResponses = noteService.getAllUserNotes(userId);
            return ResponseEntity.ok(noteResponses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
