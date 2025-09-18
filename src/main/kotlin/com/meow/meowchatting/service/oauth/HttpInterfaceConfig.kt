package com.meow.meowchatting.service.oauth

import com.meow.meowchatting.service.oauth.kakao.KakaoApiClient
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

class HttpInterfaceConfig {

    @Bean
    fun kakaoApiClient(): KakaoApiClient {
        return createHttpInterface(KakaoApiClient::class.java)
    }

    private fun <T> createHttpInterface(clazz: Class<T>): T {
        val webClient = WebClient.create()
        val factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build()

        return factory.createClient(clazz)
    }
}
