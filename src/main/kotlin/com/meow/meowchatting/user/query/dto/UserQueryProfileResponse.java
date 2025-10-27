package com.meow.meowchatting.user.query.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserQueryProfileResponse {
    private String userName;
    private  byte[] userProfile;

    @Builder
    public UserQueryProfileResponse(String userName, byte[] userProfile) {
        this.userName = userName;
        this.userProfile = userProfile;
    }

    public static  UserQueryProfileResponse of(String userName, byte[] userProfile){
        return UserQueryProfileResponse.builder()
                .userName(userName)
                .userProfile(userProfile)
                .build();
    }

}
