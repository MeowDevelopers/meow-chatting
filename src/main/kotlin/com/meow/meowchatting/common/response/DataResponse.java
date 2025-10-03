package com.meow.meowchatting.common.response;

import com.meow.meowchatting.common.exception.MeowCode;

import lombok.Getter;

@Getter
public class DataResponse<T> {

	private final int code;

	private final String message;

	private T data;

	public DataResponse(MeowCode meowCode, T data) {
		this.code = meowCode.getResponseCode();
		this.message = meowCode.getResponseMessage();
		this.data = data;
	}

	public DataResponse(MeowCode meowCode) {
		this.code = meowCode.getResponseCode();
		this.message = meowCode.getResponseMessage();
	}

}
