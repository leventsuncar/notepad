package com.lsuncar.notepad.service.impl;

import com.lsuncar.notepad.uto.req.NoteRequest;
import com.lsuncar.notepad.uto.req.ShareNoteRequest;
import com.lsuncar.notepad.uto.req.PermissionRequest;
import com.lsuncar.notepad.core.exception.EntityNotFoundException;
import com.lsuncar.notepad.db.dao.CompanyDAO;
import com.lsuncar.notepad.db.dao.CompanyUserDAO;
import com.lsuncar.notepad.db.dao.NoteDAO;
import com.lsuncar.notepad.db.dao.PermissionDAO;
import com.lsuncar.notepad.db.dao.UserDAO;
import com.lsuncar.notepad.dto.CompanyDTO;
import com.lsuncar.notepad.dto.CompanyUserDTO;
import com.lsuncar.notepad.dto.NoteDTO;
import com.lsuncar.notepad.dto.PermissionDTO;
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

    @Autowired
    private CompanyUserDAO companyUserDAO;

    @Autowired
    private PermissionDAO permissionDAO;

    @Autowired
    private CompanyDAO companyDAO;

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

    @Override
    public NoteDTO shareNote(ShareNoteRequest shareNoteRequest) throws Exception {
        try {
            NoteDTO noteDTO = noteDAO.findNoteById(shareNoteRequest.getNoteId());
            if (nonNull(noteDTO)) //Note exist
            {
                List<PermissionRequest> permissionRequests = shareNoteRequest.getPermissionRequests();

                for (PermissionRequest permissionRequest : permissionRequests) {
                    PermissionDTO permissionDTO = new PermissionDTO();
                    permissionDTO.setNote(noteDTO);
                    permissionDTO.setActive( true );
                    permissionDTO.setCreatedAt( System.currentTimeMillis() );

                    if (nonNull(permissionRequest.getCompanyPermissions())) {
                        CompanyDTO companyDTO = companyDAO.findCompanyById(permissionRequest.getCompanyPermissions().getCompanyId());
                        if (nonNull(companyDTO)) {
                            permissionDTO.setCompany(companyDTO);
                            permissionDTO.setAccessLevel(permissionRequest.getCompanyPermissions().getAccessLevel());
                        } else
                            throw new EntityNotFoundException("Company Not Found");
                    }

                    if (nonNull(permissionRequest.getUserPermissions())) {
                        UserDTO userDTO = userDAO.findUserById(permissionRequest.getUserPermissions().getUserId());
                        if (nonNull(userDTO)) {
                            permissionDTO.setUser(userDTO);
                            permissionDTO.setAccessLevel(permissionRequest.getUserPermissions().getAccessLevel());
                        } else
                            throw new EntityNotFoundException("User Not Found");
                    }
                    permissionDAO.updatePermission( permissionDTO );
                }
                throw new Exception("User Ids cannot be EMPTY");
            } else throw new EntityNotFoundException("Selected Note Not Found!");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<NoteDTO> getAllUserNotes(Long userId) throws Exception {
        try {
            CompanyUserDTO companyUserDTO = companyUserDAO.findByUserId(userId);
            Long companyId = null;
            if (nonNull(companyUserDTO))
                companyId = companyUserDTO.getCompany().getId();

            List<NoteDTO> noteDTOList = noteDAO.findAllNotesByUser(userId, companyId);
//            List<NoteDTO> responseNotes = new ArrayList<>();
//            if (nonNull(noteDTOList) && !noteDTOList.isEmpty()) {
//                for (NoteDTO noteDTO : noteDTOList) {
//
//                }
//                return responseNotes;
//            }
            return noteDTOList;
        } catch (Exception e) {
            throw e;
        }
    }
}
