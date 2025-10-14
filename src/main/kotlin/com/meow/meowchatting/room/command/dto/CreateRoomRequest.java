package com.meow.meowchatting.room.command.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.meow.meowchatting.room.command.enums.RoomType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateRoomRequest {

	@NotNull(message = "채팅방 타입은 필수입니다.")
	private RoomType roomType;

	@Length(min = 1, max = 100, message = "채팅방 이름은 최소 1자 이상, 최대 100자 이하입니다.")
	private String roomName;

	@NotEmpty(message = "채팅방 멤버 고유 번호는 필수입니다.")
	private List<Integer> memberIds;

}
