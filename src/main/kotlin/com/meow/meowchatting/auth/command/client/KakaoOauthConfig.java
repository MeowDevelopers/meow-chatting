package com.meow.meowchatting.auth.command.client;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoOauthConfig {
    private final ClientRegistration kakaoRegistration;

    public KakaoOauthConfig(ClientRegistrationRepository repository) {
        this.kakaoRegistration = repository.findByRegistrationId("kakao");
    }

    public String getClientId() { return kakaoRegistration.getClientId(); }
    public String getClientSecret() { return kakaoRegistration.getClientSecret(); }
    public String getRedirectUri() { return kakaoRegistration.getRedirectUri(); }
    public String getAuthorizationUri() { return kakaoRegistration.getProviderDetails().getAuthorizationUri(); }
}
