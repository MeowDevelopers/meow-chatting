package com.meow.meowchatting.common.response;

import com.meow.meowchatting.common.exception.MeowCode;

import lombok.Getter;

@Getter
public class DataResponse<T> {

	private final MeowCode meowCode;

	private final String message;

	private T data;

	public DataResponse(MeowCode meowCode, T data) {
		this.meowCode = meowCode;
		this.message = meowCode.getResponseMessage();
		this.data = data;
	}

	public DataResponse(MeowCode meowCode) {
		this.meowCode = meowCode;
		this.message = meowCode.getResponseMessage();
	}

}
