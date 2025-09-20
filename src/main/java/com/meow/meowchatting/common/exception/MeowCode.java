package com.meow.meowchatting.common.exception;

/**
 * [Meow Code 명명 규칙]
 * - 클래스 : Meow{도메인명}Code
 * - Code : MEOW_{도메인명}_{RESPONSE CODE 명칭}
 */
public interface MeowCode {

	int getResponseCode();

	String getResponseMessage();

}
