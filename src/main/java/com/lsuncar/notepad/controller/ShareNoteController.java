package com.lsuncar.notepad.controller;

import com.lsuncar.notepad.controller.req.ShareNoteRequest;
import com.lsuncar.notepad.dto.UserSharedNotesDTO;
import com.lsuncar.notepad.service.UserSharedNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/note/shared/" )
public class ShareNoteController {

    @Autowired
    private UserSharedNotesService userSharedNotesService;

    @PostMapping("share")
    public ResponseEntity<?> share(@RequestBody ShareNoteRequest shareNoteRequest) {
        try {
            UserSharedNotesDTO addedUserSharedNotesDTO = userSharedNotesService.add(shareNoteRequest);
            return ResponseEntity.ok(addedUserSharedNotesDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ErrorResult");
        }
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<?> getSharedNotesByUserId(@PathVariable Long userId) {
        try {
            List<UserSharedNotesDTO> userSharedNotesDTOList = userSharedNotesService.findAllUserSharedNotesByUserId(userId);
            return ResponseEntity.ok(userSharedNotesDTOList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ErrorResult");
        }
    }

    @GetMapping("note/{noteId}")
    public ResponseEntity<?> getSharedNotesByNoteId(@PathVariable Long noteId) {
        try {
            List<UserSharedNotesDTO> userSharedNotesDTOList = userSharedNotesService.findAllUserSharedNotesByNoteId(noteId);
            return ResponseEntity.ok(userSharedNotesDTOList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ErrorResult");
        }
    }

    @DeleteMapping( "delete" )
    public ResponseEntity<?> deleteSharedNote(@RequestBody UserSharedNotesDTO userSharedNotesDTO) {
        try {
            userSharedNotesService.deletePermanentlyUserSharedNote(userSharedNotesDTO);
            return ResponseEntity.ok("SuccesResult");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ErrorResult");
        }
    }

}
