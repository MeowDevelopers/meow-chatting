package com.meow.meowchatting.service.user.enums

import lombok.Getter

@Getter
enum class ProviderType(val description: String) {
    KAKAO("카카오"),
    ORIGIN("자체회원가입");
}
