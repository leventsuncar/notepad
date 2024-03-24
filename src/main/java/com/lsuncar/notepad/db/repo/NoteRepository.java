package com.lsuncar.notepad.db.repo;

import com.lsuncar.notepad.db.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findNotesByOwner_Id(Long userId);

    @Query ( "SELECT Note FROM Note n JOIN Permission p ON n.id = p.note.id" +
            " WHERE p.user.id = :userId or ( p.company.id = :companyId and p.user.id IS NULL )" )
    List<Note> findAllNotesByUserId (@Param( "userId" ) Long userId, @Param( "companyId" ) Long companyId );
}
