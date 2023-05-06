package com.lsuncar.notepad.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table ( name = "note" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note extends BaseEntity
{
	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO )
	@Column ( name = "id" )
	private Long id;

	@Column ( name = "title" )
	@NotNull
	@NotBlank
	@NotEmpty
	private String title;

	@Column ( name = "content" )
	@NotNull
	@NotBlank
	@NotEmpty
	private String content;

	@Column ( name = "active" )
	private Boolean active;

	@JsonIgnore
	@ManyToOne
	@JoinColumn ( name = "user_id", nullable = false, referencedColumnName = "id")
	private User user;

	//TODO notlar paylaşılabilir olacak
}
