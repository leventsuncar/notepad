package com.lsuncar.notepad.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity
{

	@Column ( name = "created_at" )
	private Long createdAt;

	@Column ( name = "updated_at" )
	private Long updatedAt;

	@Column ( name = "active" )
	private Boolean active;

	public Long getCreatedAt ()
	{
		return createdAt;
	}

	public void setCreatedAt ( Long createdAt )
	{
		this.createdAt = createdAt;
	}

	public Long getUpdatedAt ()
	{
		return updatedAt;
	}

	public void setUpdatedAt ( Long updatedAt )
	{
		this.updatedAt = updatedAt;
	}

	public Boolean getActive ()
	{
		return active;
	}

	public void setActive ( Boolean active )
	{
		this.active = active;
	}
}
