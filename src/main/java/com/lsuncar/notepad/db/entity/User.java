package com.lsuncar.notepad.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table ( name = "user_" )
@Data
public class User extends BaseEntity implements UserDetails
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

	@Column ( name = "password" )
	@NotNull
	@NotBlank
	@NotEmpty
	private String password;

	@JsonIgnore
	@OneToMany ( mappedBy = "user" , fetch = FetchType.LAZY)
	private List<Note> notes;

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities ()
	{
		return null;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired ()
	{
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked ()
	{
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired ()
	{
		return true;
	}

	@Override
	public boolean isEnabled ()
	{
		return this.getActive();
	}



}
