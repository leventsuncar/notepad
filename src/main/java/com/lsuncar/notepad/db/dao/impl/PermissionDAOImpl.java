package com.lsuncar.notepad.db.dao.impl;

import com.lsuncar.notepad.db.dao.PermissionDAO;
import com.lsuncar.notepad.db.entity.Permission;
import com.lsuncar.notepad.db.repo.PermissionRepository;
import com.lsuncar.notepad.dto.PermissionDTO;
import com.lsuncar.notepad.dto.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissionDAOImpl implements PermissionDAO {

    @Autowired
    private PermissionRepository permissionRepository;

    private PermissionMapper getMapper() {
        return PermissionMapper.INSTANCE;
    }

    @Override
    public PermissionDTO updatePermission(PermissionDTO permissionDTO) {
        try {
            Permission permission = getMapper().toEntity(permissionDTO);
            Permission savedPermission = permissionRepository.save(permission);
            PermissionDTO savedPermissionDTO = getMapper().toDto(savedPermission);
            return savedPermissionDTO;
        } catch (Exception e) {
            throw e;
        }
    }
}
