package com.meow.meowchatting.auth.command.client;

import com.meow.meowchatting.auth.command.dto.KakaoUserResponse;
import com.meow.meowchatting.auth.command.dto.OauthToken;
import com.meow.meowchatting.auth.command.enums.AuthResponseCode;
import com.meow.meowchatting.auth.command.exception.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;


@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoApiClient {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://kauth.kakao.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .build();


    public OauthToken fetchToken(MultiValueMap<String, String> params) {
        try {
            return webClient.post()
                    .uri("/oauth/token")
                    .body(BodyInserters.fromFormData(params))
                    .retrieve()
                    .bodyToMono(OauthToken.class)
                    .block();
        } catch ( WebClientResponseException e ) {
            log.error("[Kakao API Error] status={}, uri={}, response={}",
                    e.getStatusCode(),
                    e.getRequest().getURI(),
                    e.getResponseBodyAsString());
            throw new AuthException(AuthResponseCode.INVALID_AUTH_CODE);
        }
    }


    public KakaoUserResponse fetchMember(String bearerToken) {
        try {
            return WebClient.create("https://kapi.kakao.com")
                    .get()
                    .uri("/v2/user/me")
                    .header(HttpHeaders.AUTHORIZATION, bearerToken)
                    .retrieve()
                    .bodyToMono(KakaoUserResponse.class)
                    .block();
        } catch (WebClientResponseException e) {
            log.error("[Kakao API Error] status={}, uri={}, response={}",
                    e.getStatusCode(),
                    e.getRequest().getURI(),
                    e.getResponseBodyAsString());
            throw new AuthException(AuthResponseCode.INVALID_ACCESS_TOKEN);
        }
    }
}
