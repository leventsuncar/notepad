package com.lsuncar.notepad.uto.req;

import lombok.Data;

import java.util.List;

@Data
public class ShareNoteRequest
{
	private List<PermissionRequest> permissionRequests;
	private Long noteId;
}
