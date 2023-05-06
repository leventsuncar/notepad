package com.lsuncar.notepad.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table ( name = "user_" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity
{
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column ( name = "id" )
	private Long id;

	@Column ( name = "uname", unique = true )
	@NotNull
	@NotBlank
	@NotEmpty
	private String username;

	@Column ( name = "email", unique = true)
	@Email
	@NotNull
	@NotBlank
	@NotEmpty
	private String email;

	@Column ( name = "passwd" )
	@NotNull
	@NotBlank
	@NotEmpty
	private String passwd;

	@OneToMany ( mappedBy = "user" )
	private List<Note> notes;
}
