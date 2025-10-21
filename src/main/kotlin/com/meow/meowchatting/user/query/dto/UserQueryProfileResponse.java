package com.meow.meowchatting.user.query.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserQueryProfileResponse {
    private String userName;
    private byte[] userProfile;

    @Builder
    public UserQueryProfileResponse(String userName, byte[] userProfile) {
        this.userName = userName;
        this.userProfile = userProfile;
    }
}
