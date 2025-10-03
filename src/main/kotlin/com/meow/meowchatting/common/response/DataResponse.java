package com.meow.meowchatting.common.response;

import com.meow.meowchatting.common.exception.MeowCode;

import lombok.Getter;

@Getter
public class DataResponse<T> {


	private final MeowCode meowCode;

	private final String message;

	private T response;

	public DataResponse(MeowCode meowCode, T response) {
		this.meowCode = meowCode;
		this.message = meowCode.getResponseMessage();
		this.response = response;
	}

	public DataResponse(MeowCode meowCode) {
		this.meowCode = meowCode;
		this.message = meowCode.getResponseMessage();
	}

	public DataResponse(String message) {
		this.meowCode = null;
		this.message = message;
	}

}
