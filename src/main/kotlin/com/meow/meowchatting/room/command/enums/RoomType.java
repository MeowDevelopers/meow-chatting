package com.meow.meowchatting.room.command.enums;

import lombok.Getter;

@Getter
public enum RoomType {

	DIRECT("1:1 채팅"),

	GROUP("그룹 채팅");

	private final String description;

	RoomType(String description) {
		this.description = description;
	}

}
