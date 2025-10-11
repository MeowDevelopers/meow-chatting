package com.meow.meowchatting.auth.command.client

import com.meow.meowchatting.auth.command.enums.ProviderType
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component


@Component
class ProviderTypeConverter : Converter<String, ProviderType> {
    override fun convert(source: String): ProviderType {
        return ProviderType.fromName(source)
            ?: throw IllegalArgumentException("Unsupported provider type: $source")
    }
}
