package com.meow.meowchatting.auth.command.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meow.meowchatting.auth.command.client.KakaoApiClient;
import com.meow.meowchatting.auth.command.client.KakaoOauthConfig;
import com.meow.meowchatting.auth.command.dto.KakaoLoginResponseDto;
import com.meow.meowchatting.auth.command.dto.KakaoUserResponse;
import com.meow.meowchatting.auth.command.dto.OauthToken;
import com.meow.meowchatting.auth.command.enums.AuthResponseCode;
import com.meow.meowchatting.auth.command.exception.AuthException;
import com.meow.meowchatting.common.response.DataResponse;
import com.meow.meowchatting.jwt.JwtProvider;
import com.meow.meowchatting.user.command.domain.RefreshToken;
import com.meow.meowchatting.user.command.domain.User;
import com.meow.meowchatting.user.command.domain.UserProfile;
import com.meow.meowchatting.user.repository.RefreshTokenRepository;
import com.meow.meowchatting.user.repository.UserCommandRepository;
import com.meow.meowchatting.user.repository.UserProfileRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Stream;

@Service
public class KakaoAuthService {

    private final KakaoOauthConfig kakaoOauthConfig;
    private final KakaoApiClient kakaoApiClient;
    private final UserCommandRepository userCommandRepository;
    private final UserProfileRepository userProfileRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    public KakaoAuthService(KakaoOauthConfig kakaoOauthConfig, KakaoApiClient kakaoApiClient,
                            UserCommandRepository userCommandRepository, UserProfileRepository userProfileRepository,
                            RefreshTokenRepository refreshTokenRepository, JwtProvider jwtProvider) {
        this.kakaoOauthConfig = kakaoOauthConfig;
        this.kakaoApiClient = kakaoApiClient;
        this.userCommandRepository = userCommandRepository;
        this.userProfileRepository = userProfileRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtProvider = jwtProvider;
    }

    private void validateOauthConfig(){
        if (Stream.of(
                kakaoOauthConfig.getAuthorizationUri(),
                kakaoOauthConfig.getClientId(),
                kakaoOauthConfig.getRedirectUri(),
                kakaoOauthConfig.getClientSecret()
                ).anyMatch(ObjectUtils::isEmpty)) {
            throw new AuthException(AuthResponseCode.OAUTH_CONFIG_ERROR);
        }
    }

    public String getAuthCodeRequestUrl(){
        validateOauthConfig();
        try {
            return UriComponentsBuilder
                    .fromHttpUrl(kakaoOauthConfig.getAuthorizationUri())
                    .queryParam("response_type", "code")
                    .queryParam("client_id", kakaoOauthConfig.getClientId())
                    .queryParam("redirect_uri", kakaoOauthConfig.getRedirectUri())
                    .toUriString();
        } catch (IllegalArgumentException e) {
            throw new AuthException(AuthResponseCode.INVALID_AUTH_URI);
        }
    }


    public ResponseEntity<DataResponse<KakaoLoginResponseDto>> login(String authCode) throws JsonProcessingException {
        MultiValueMap<String, String> requestParams = tokenRequestParams(authCode);
        OauthToken tokenInfo = kakaoApiClient.fetchToken(requestParams);
        KakaoUserResponse kakaoUser = kakaoApiClient.fetchMember("Bearer "+ tokenInfo.getAccessToken());

        User user = userCommandRepository.findByUserEmail(kakaoUser.kakaoAccount().email())
                .orElseGet(()-> userCommandRepository.save(kakaoUser.toUserEntity()));

        UserProfile userProfile = userProfileRepository.findByUserId(user.getId())
                .orElseGet(() -> userProfileRepository.save(kakaoUser.toUserProfileEntity(user.getId())));

        String accessToken = jwtProvider.generateToken(user.getUserEmail(),List.of("USER"));
        String refreshToken = jwtProvider.generateRefreshToken(user.getUserEmail(), List.of("USER"));

        RefreshToken refreshTokenEntity = refreshTokenRepository.findByUserId(user.getId())
                .map(existing -> existing.update(refreshToken))
                .orElseGet(() -> refreshTokenRepository.save(new RefreshToken(user.getId(), refreshToken)));
        return ResponseEntity.ok(
                new DataResponse<>(AuthResponseCode.LOGIN_SUCCESS,
                        KakaoLoginResponseDto.of(user, userProfile, accessToken, refreshToken)
                )
        );
    }
    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        validateOauthConfig();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoOauthConfig.getClientId());
        params.add("redirect_uri", kakaoOauthConfig.getRedirectUri());
        params.add("code", authCode);
        params.add("client_secret", kakaoOauthConfig.getClientSecret());
        return params;
    }
}
