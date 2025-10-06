package com.meow.meowchatting.auth.command.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meow.meowchatting.auth.command.dto.KakaoUserResponse;
import com.meow.meowchatting.auth.command.dto.OauthToken;
import com.nimbusds.jose.shaded.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;


@Service
@RequiredArgsConstructor
public class KakaoApiClient {
    private final ObjectMapper objectMapper;

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://kauth.kakao.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .build();


    public OauthToken fetchToken(MultiValueMap<String, String> params) {
        String rawResponse = webClient.post()
                .uri("/oauth/token")
                .body(BodyInserters.fromFormData(params))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return new Gson().fromJson(rawResponse, OauthToken.class);
    }


    public KakaoUserResponse fetchMember(String bearerToken) throws JsonProcessingException {
        String rawResponse = WebClient.create("https://kapi.kakao.com")
                .get()
                .uri("/v2/user/me")
                .header(HttpHeaders.AUTHORIZATION, bearerToken)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return objectMapper.readValue(rawResponse, KakaoUserResponse.class);
    }
}
