package com.meow.meowchatting.user.command.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FriendNameUpdateResponse {

	private Long friendId;

	private String userProfile;

	private String friendName;

	@Builder
	public FriendNameUpdateResponse(Long friendId, String userProfile, String friendName) {
		this.friendId = friendId;
		this.userProfile = userProfile;
		this.friendName = friendName;
	}

	public static FriendNameUpdateResponse of(Long friendId, String userProfile, String friendName) {
		return FriendNameUpdateResponse.builder()
			.friendId(friendId)
			.userProfile(userProfile)
			.friendName(friendName)
			.build();
	}

}
