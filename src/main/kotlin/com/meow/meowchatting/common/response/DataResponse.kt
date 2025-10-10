package com.meow.meowchatting.common.response

data class DataResponse<T> (
    val status: Int,
    val message: String,
    val response: T? = null
) {
    constructor(status: Int, message: String) : this(
        status = status,
        message = message,
        response = null
    )
}
