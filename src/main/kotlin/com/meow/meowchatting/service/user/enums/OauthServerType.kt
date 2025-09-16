package com.meow.meowchatting.service.user.enums

import java.util.Locale

enum class OauthServerType(val description: String) {
    KAKAO("카카오");

    companion object {
        fun fromName(type: String): OauthServerType =
            valueOf(type.uppercase(Locale.ENGLISH))
    }
}
