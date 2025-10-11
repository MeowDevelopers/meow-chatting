package com.meow.meowchatting.room.command.domain;

import com.meow.meowchatting.common.base.AbstractBaseUserByEntity;
import com.meow.meowchatting.room.command.enums.RoomType;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room")
@Getter @NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "room_id"))
public class Room extends AbstractBaseUserByEntity {

	@Column(nullable = false, name = "room_type")
	private RoomType roomType;

	@Column(nullable = false, name = "room_name", length = 100)
	private String roomName;

	@Column(nullable = false, name = "room_owner_user_id")
	private Long roomOwnerUserId;

}
