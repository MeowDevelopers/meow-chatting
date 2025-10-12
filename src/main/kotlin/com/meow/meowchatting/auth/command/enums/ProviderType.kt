package com.meow.meowchatting.auth.command.enums

import com.fasterxml.jackson.annotation.JsonCreator
import java.util.Locale

enum class ProviderType {
    KAKAO;

    companion object {
        @JsonCreator
        @JvmStatic
        fun fromName(type: String?): ProviderType? =
            type
                ?.takeIf { it.isNotBlank() }
                ?.uppercase(Locale.getDefault())
                ?.let { upper -> entries.firstOrNull { it.name == upper } }
    }
}

