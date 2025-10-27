package com.meow.meowchatting.s3.exception

import com.meow.meowchatting.common.exception.MeowCode
import com.meow.meowchatting.common.exception.MeowException

class AwsS3Exception(private val meowCode: MeowCode): MeowException(meowCode) {
}
