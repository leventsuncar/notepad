package com.lsuncar.notepad.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table ( name = "user_" )
@Data
@NoArgsConstructor
@AllArgsConstructor
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
	@OneToMany ( mappedBy = "user" )
	private List<Note> notes;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities ()
	{
		return null;
	}

	@Override
	public boolean isAccountNonExpired ()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked ()
	{
		return true;
	}

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
