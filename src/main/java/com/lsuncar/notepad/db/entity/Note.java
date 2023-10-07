package com.lsuncar.notepad.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table ( name = "note" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note extends BaseEntity
{
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private Long id;

	@Column ( name = "title" )
	private String title;

	@Column ( name = "content" )
	private String content;

	@ManyToOne
	private User owner;

	@ManyToMany
	@JoinTable ( name = "user_notes", joinColumns = @JoinColumn ( name = "note_id" ), inverseJoinColumns = @JoinColumn ( name = "user_id" ) )
	private List<User> sharedUsers = new ArrayList<>();
}
