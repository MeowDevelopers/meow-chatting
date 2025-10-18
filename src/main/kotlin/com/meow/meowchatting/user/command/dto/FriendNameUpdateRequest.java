package com.meow.meowchatting.user.command.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FriendNameUpdateRequest {

	@NotNull(message = "친구 ID는 필수입니다.")
	private Long friendId;

	@NotBlank(message = "친구 이름은 필수입니다.")
	@Length(min = 1, max = 30, message = "친구 이름은 최소 1자 이상, 최대 30자 이하입니다.")
	private String friendName;

}
