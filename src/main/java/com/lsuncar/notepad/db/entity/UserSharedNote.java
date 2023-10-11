package com.lsuncar.notepad.db.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@Table(name = "user_shared_notes", schema = "public")
public class UserSharedNote extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "noteid", referencedColumnName = "id", nullable = false)
    private Note note;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private User user;

}
