package com.meow.meowchatting.auth.command.dto;

import com.meow.meowchatting.auth.command.enums.ProviderType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoLoginRequestDto {
    private ProviderType providerType;
    private String code;

    public KakaoLoginRequestDto(ProviderType providerType, String code){
        this.providerType = providerType;
        this.code = code;
    }
}
