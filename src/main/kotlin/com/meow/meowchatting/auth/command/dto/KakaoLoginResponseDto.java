package com.meow.meowchatting.auth.command.dto;

import com.meow.meowchatting.user.command.domain.RefreshToken;
import com.meow.meowchatting.user.command.domain.User;
import com.meow.meowchatting.user.command.domain.UserProfile;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoLoginResponseDto {

    private Long userId;
    private String userName;
    private String profileUrl;
    private String accessToken;
    private String refreshToken;

    @Builder
    public KakaoLoginResponseDto(Long userId, String userName, String profileUrl, String accessToken, String refreshToken) {
        this.userId = userId;
        this.userName = userName;
        this.profileUrl = profileUrl;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static KakaoLoginResponseDto of(User user, UserProfile userProfile, String accessToken, String refreshToken){
        return KakaoLoginResponseDto.builder()
                .userId(user.getId())
                .userName(user.getUserName())
                .profileUrl(userProfile.getUserProfileUrl())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
