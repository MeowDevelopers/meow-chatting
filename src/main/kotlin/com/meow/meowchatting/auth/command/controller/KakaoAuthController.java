package com.meow.meowchatting.auth.command.controller;

import com.meow.meowchatting.auth.command.dto.KakaoLoginRequestDto;
import com.meow.meowchatting.auth.command.dto.KakaoLoginResponseDto;
import com.meow.meowchatting.auth.command.enums.AuthResponseCode;
import com.meow.meowchatting.auth.command.enums.ProviderType;
import com.meow.meowchatting.auth.command.exception.AuthException;
import com.meow.meowchatting.auth.command.service.KakaoAuthService;
import com.meow.meowchatting.common.response.DataResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/oauth", name = "카카오 인증 컨트롤러")
@RestController
public class KakaoAuthController {

    private final KakaoAuthService kakaoAuthService;

    public KakaoAuthController(KakaoAuthService kakaoAuthService) {
        this.kakaoAuthService = kakaoAuthService;
    }

    @GetMapping(value = "/{providerType}", name = "인가코드 받는 API")
    public DataResponse<String> redirectAuthCodeRequestUrl(@PathVariable("providerType") ProviderType providerType){
        if (ObjectUtils.isEmpty(providerType)) {
            throw new AuthException(AuthResponseCode.OAUTH_CONFIG_ERROR);
        };

        String redirectUrl = kakaoAuthService.getAuthCodeRequestUrl();
        return new DataResponse<>(AuthResponseCode.OAUTH_AUTHORIZE_SUCCESS, redirectUrl);

    }

    @GetMapping("/kakao/callback")
    public ResponseEntity<DataResponse<KakaoLoginResponseDto>> kakaoCallback(
            @RequestParam("code") String code) {
        return kakaoAuthService.login(code);
    }

    @PostMapping(value = "/login", name = "카카오 토큰 발급 API")
    public ResponseEntity<DataResponse<KakaoLoginResponseDto>> login(
            @RequestBody KakaoLoginRequestDto kakaoLoginRequestDto) {
        return kakaoAuthService.login(kakaoLoginRequestDto.getCode());
    }
}
