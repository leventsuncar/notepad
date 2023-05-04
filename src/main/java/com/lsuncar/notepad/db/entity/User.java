package com.lsuncar.notepad.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.List;

@Entity
@Table ( name = "user_" )
@Data
public class User extends BaseEntity
{
	@Id
	@Column ( name = "id" )
	private Long id;

	@Column ( name = "uname" )
	private String username;

	@Column ( name = "passwd" )
	private String passwd;

	@OneToMany ( mappedBy = "user" )
	private List<Note> notes;
}
