package com.meow.meowchatting.service.oauth

import com.meow.meowchatting.service.user.enums.OauthServerType
import org.springframework.core.convert.converter.Converter

class OauthServerTypeConverter : Converter<String, OauthServerType> {

    override fun convert(type: String): OauthServerType {
        return OauthServerType.fromName(type);
    }
}
