package com.meow.meowchatting.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PagingRequest {

	@NotNull(message = "요청 페이지는 필수입니다.")
	private int page;

	@NotNull(message = "페이지 당 조회할 데이터 수는 필수입니다.")
	private int size;

	@Builder
	public PagingRequest(int page, int size) {
		this.page = page;
		this.size = size;
	}

	public static PagingRequest of(int page, int size) {
		return PagingRequest.builder()
			.page(page)
			.size(size)
			.build();
	}
}
