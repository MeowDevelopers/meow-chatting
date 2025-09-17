package com.meow.meowchatting.controller.user

import com.meow.meowchatting.service.user.enums.OauthServerType
import com.meow.meowchatting.service.user.service.OauthService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class OauthController(
    private val oauthService : OauthService
) {

    @GetMapping("/{oauthServerType}")
    fun redirectAuthCodeRequestUrl(@PathVariable("oauthServerType") oauthServerType: OauthServerType): ResponseEntity<String> {
        val requestUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);
        return ResponseEntity.ok(requestUrl)
    }
}
