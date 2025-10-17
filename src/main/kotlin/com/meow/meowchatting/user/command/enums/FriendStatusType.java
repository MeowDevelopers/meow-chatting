package com.meow.meowchatting.user.command.enums;

import lombok.Getter;

@Getter
public enum FriendStatusType {

	FRIEND("친구"),

	BLOCKED("차단"),

	REMOVED("삭제");

	private final String description;

	FriendStatusType(String description) {
		this.description = description;
	}

}
