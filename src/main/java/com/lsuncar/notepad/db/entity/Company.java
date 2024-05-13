package com.lsuncar.notepad.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "company", schema = "public")
@Data
public class Company extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column( name = "name")
    @NotNull( message =  "fieldCannotBeNull-name" )
    @NotBlank( message =  "fieldCannotBeBlank-name" )
    private String name;

    /* www.hostName.com -> maybe the user email may be associated with the company host name. */
    @Column( name = "hostname", unique = true)
    @NotNull( message = "fieldCannotBeNull-hostName")
    @NotBlank( message =  "fieldCannotBeBlank-hostName")
    private String hostName;
}
