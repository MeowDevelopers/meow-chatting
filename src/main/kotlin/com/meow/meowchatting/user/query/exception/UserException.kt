package com.meow.meowchatting.user.query.exception

import com.meow.meowchatting.common.exception.MeowCode
import com.meow.meowchatting.common.exception.MeowException

class UserException (private val meowCode: MeowCode) : MeowException(meowCode){
}
