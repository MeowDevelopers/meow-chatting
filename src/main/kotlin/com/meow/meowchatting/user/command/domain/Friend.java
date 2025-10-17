package com.meow.meowchatting.user.command.domain;

import com.meow.meowchatting.common.base.AbstractBaseUserByEntity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "friend")
@Getter @NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "friend_id"))
public class Friend extends AbstractBaseUserByEntity {

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "friend_user_id", nullable = false)
	private Long friendUserId;

	@Column(name = "friend_name", nullable = false, length = 30)
	private String friendName;

	@Column(name = "is_blocked", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
	private Boolean isBlocked = false;

	/**
	 *  친구 이름 변경
	 */
	public void updateFriendName(String friendName) {
		this.friendName = friendName;
	}

}
