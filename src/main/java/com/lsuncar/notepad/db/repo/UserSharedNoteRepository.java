package com.lsuncar.notepad.db.repo;

import com.lsuncar.notepad.db.entity.UserSharedNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSharedNoteRepository extends JpaRepository<UserSharedNote, Long> {

    List<UserSharedNote> findAllByUser_Id(Long userId);

    List<UserSharedNote> findAllByNote_Id(Long userId);
}
