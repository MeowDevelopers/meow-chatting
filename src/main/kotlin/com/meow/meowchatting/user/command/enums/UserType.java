package com.meow.meowchatting.user.command.enums;

import lombok.Getter;

@Getter
public enum UserType {

	ROLE_USER("유저");

	private String description;

	UserType(String description) {
		this.description = description;
	}

}
