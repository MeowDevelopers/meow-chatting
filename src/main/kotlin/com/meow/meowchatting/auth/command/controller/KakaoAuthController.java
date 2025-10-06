package com.meow.meowchatting.auth.command.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meow.meowchatting.auth.command.dto.KakaoLoginRequestDto;
import com.meow.meowchatting.auth.command.dto.KakaoLoginResponseDto;
import com.meow.meowchatting.auth.command.dto.KakaoUserResponse;
import com.meow.meowchatting.auth.command.enums.AuthResponseCode;
import com.meow.meowchatting.auth.command.service.KakaoAuthService;
import com.meow.meowchatting.common.response.DataResponse;
import com.meow.meowchatting.user.command.enums.ProviderType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth")
@RestController
public class KakaoAuthController {

    private final KakaoAuthService kakaoAuthService;

    public KakaoAuthController(KakaoAuthService kakaoAuthService) {
        this.kakaoAuthService = kakaoAuthService;
    }

    @GetMapping("/{providerType}")
    public DataResponse<String> redirectAuthCodeRequestUrl(@PathVariable String providerType){
        ProviderType type = ProviderType.Companion.fromName(providerType);
        if (ObjectUtils.isEmpty(type)){
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }

        String redirectUrl = kakaoAuthService.getAuthCodeRequestUrl();
        return new DataResponse<>(AuthResponseCode.OAUTH_AUTHORIZE_SUCCESS, redirectUrl);

    }

    @PostMapping("/login/{providerType}")
    public ResponseEntity<DataResponse<KakaoLoginResponseDto>> login(
            @PathVariable String providerType, @RequestBody KakaoLoginRequestDto kakaoLoginRequestDto) throws JsonProcessingException{
        return kakaoAuthService.login(kakaoLoginRequestDto.getCode());
    }
}
