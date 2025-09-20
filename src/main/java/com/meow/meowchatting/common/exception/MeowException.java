package com.meow.meowchatting.common.exception;

import lombok.Getter;

@Getter
public class MeowException extends RuntimeException {

	private final MeowCode meowCode;

	public MeowException(MeowCode meowCode) {
		this.meowCode = meowCode;
	}

}
