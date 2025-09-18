package com.meow.meowchatting.service.user.enums

import lombok.Getter

@Getter
enum class UserType(val description: String) {
    ADMIN("관리자"),
    BASIC_USER("유저");
}
