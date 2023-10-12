package com.lsuncar.notepad.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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