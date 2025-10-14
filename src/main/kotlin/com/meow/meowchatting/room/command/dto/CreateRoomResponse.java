package com.meow.meowchatting.room.command.dto;

import java.util.List;

import com.meow.meowchatting.room.command.enums.RoomType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateRoomResponse {

	private Long roomId;

	private RoomType roomType;

	private String roomName;

	private Long ownerUserId;

	private String previewMessage;

	private int unreadMessageCount;

	private String createdFormatted;

	private List<RoomMemberList> members;

	@Builder
	public CreateRoomResponse(Long roomId, RoomType roomType, String roomName, Long ownerUserId, String previewMessage,
		int unreadMessageCount, String createdFormatted, List<RoomMemberList> members) {
		this.roomId = roomId;
		this.roomType = roomType;
		this.roomName = roomName;
		this.ownerUserId = ownerUserId;
		this.previewMessage = previewMessage;
		this.unreadMessageCount = unreadMessageCount;
		this.createdFormatted = createdFormatted;
		this.members = members;
	}

	public static CreateRoomResponse of(Long roomId, RoomType roomType, String roomName, Long ownerUserId, String previewMessage,
		int unreadMessageCount, String createdFormatted, List<RoomMemberList> members) {
		return CreateRoomResponse.builder()
			.roomId(roomId)
			.roomType(roomType)
			.roomName(roomName)
			.ownerUserId(ownerUserId)
			.previewMessage(previewMessage)
			.unreadMessageCount(unreadMessageCount)
			.createdFormatted(createdFormatted)
			.members(members)
			.build();
	}
}
