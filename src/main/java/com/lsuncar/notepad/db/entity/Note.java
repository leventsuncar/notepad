package com.lsuncar.notepad.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table ( name = "note" )
@Data
public class Note extends BaseEntity
{
	@Id
	@Column ( name = "id" )
	private Long id;

	@Column ( name = "title" )
	private String title;

	@Column ( name = "content" )
	private String content;

	@Column ( name = "active" )
	private Boolean active;

	@ManyToOne
	@JoinColumn ( name = "user_id", nullable = false )
	private User user;
}
