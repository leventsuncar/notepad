package com.lsuncar.notepad.db.repo;

import com.lsuncar.notepad.db.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
