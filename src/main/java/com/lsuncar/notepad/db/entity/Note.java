package com.lsuncar.notepad.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "note", schema = "public", catalog = "notepad_data")
public class Note extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 256)
    private String title;

    @Column(name = "content", nullable = false, length = 256)
    private String content;

    @OneToOne
    @JoinColumn(name = "owner", referencedColumnName = "id", nullable = false)
    private User owner;

    @JsonIgnore
    @OneToMany(mappedBy = "id")
    private List<UserSharedNote> userSharedNotes;
}