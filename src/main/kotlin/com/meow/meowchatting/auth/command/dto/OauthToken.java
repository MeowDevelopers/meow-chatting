package com.meow.meowchatting.auth.command.dto;

import com.nimbusds.jose.shaded.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OauthToken {
    @SerializedName("token_type")
    String tokenType;

    @SerializedName("access_token")
    String accessToken;

    @SerializedName("id_token")
    String idToken;

    @SerializedName("expires_in")
    Integer expiresIn;

    @SerializedName("refresh_token")
    String refreshToken;

    @SerializedName("refresh_token_expires_in")
    Integer refreshTokenExpiresIn;
    String scope;
}
