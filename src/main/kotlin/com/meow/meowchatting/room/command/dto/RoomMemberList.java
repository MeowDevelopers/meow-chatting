package com.meow.meowchatting.room.command.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RoomMemberList {

	private Long userId;

	private String userName;

	private String profileUrl;

	@Builder
	public RoomMemberList(Long userId, String userName, String profileUrl) {
		this.userId = userId;
		this.userName = userName;
		this.profileUrl = profileUrl;
	}

	public static RoomMemberList of(Long userId, String userName, String profileUrl) {
		return RoomMemberList.builder()
			.userId(userId)
			.userName(userName)
			.profileUrl(profileUrl)
			.build();
	}

}
