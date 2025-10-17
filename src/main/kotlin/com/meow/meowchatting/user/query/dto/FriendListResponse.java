package com.meow.meowchatting.user.query.dto;

import com.meow.meowchatting.user.command.enums.FriendStatusType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FriendListResponse {

	private Long friendId;

	private String friendName;

	private String profileUrl;

	private FriendStatusType friendStatus;

	public FriendListResponse(Long friendId, String friendName, String profileUrl, FriendStatusType friendStatus) {
		this.friendId = friendId;
		this.friendName = friendName;
		this.profileUrl = profileUrl;
		this.friendStatus = friendStatus;
	}
}
