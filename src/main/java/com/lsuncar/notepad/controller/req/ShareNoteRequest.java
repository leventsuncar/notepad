package com.lsuncar.notepad.controller.req;

import lombok.Data;

import java.util.List;

@Data
public class ShareNoteRequest
{
	private List<Long> sharingUsers;
	private Long noteId;
}
