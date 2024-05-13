package com.lsuncar.notepad.db.entity;

import com.lsuncar.notepad.enumeration.AccessLevel;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "permission", schema = "public", catalog = "notepad_data")
public class Permission extends BaseEntity{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn ( name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn ( name = "company_id", referencedColumnName = "id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "note_id", referencedColumnName = "id", nullable = false)
    private Note note;

    @Enumerated
    private AccessLevel accessLevel;

}
