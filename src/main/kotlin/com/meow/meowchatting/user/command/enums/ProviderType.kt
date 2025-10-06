package com.meow.meowchatting.user.command.enums

import java.util.Locale

enum class ProviderType {
    KAKAO;

    companion object{
        fun fromName(type: String): ProviderType = valueOf(type.uppercase(Locale.getDefault()))
    }
}
