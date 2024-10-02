package suhyang.inkspire.infrastructure.user.dto

import suhyang.inkspire.domain.user.User

class UserResponseDto {
    data class UserResponse(
            val id: String,
            val name: String,
    ) {
        constructor(user: User): this(id = user.id, name = user.name);
    }
}