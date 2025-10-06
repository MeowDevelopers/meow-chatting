package com.meow.meowchatting.auth.command.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KakaoLoginRequestDto {
    private String code;
    public KakaoLoginRequestDto(String code){
        this.code = code;
    }
}
