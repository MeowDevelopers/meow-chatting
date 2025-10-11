package com.meow.meowchatting.auth.command.exception

import com.meow.meowchatting.common.exception.MeowCode
import com.meow.meowchatting.common.exception.MeowException

class AuthException(private val meowCode: MeowCode) : MeowException(meowCode) {
}
