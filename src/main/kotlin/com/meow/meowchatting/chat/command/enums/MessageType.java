package com.meow.meowchatting.chat.command.enums;

import lombok.Getter;

@Getter
public enum MessageType {

	TEXT("텍스트"),

	IMAGE("이미지"),

	VIDEO("영상"),

	FILE("파일");

	private final String description;

	MessageType(String description) {
		this.description = description;
	}

}
