package suhyang.inkspire.domain.user

import suhyang.inkspire.infrastructure.auth.dto.OAuthUser

interface UserRepository {
    fun getUser(id: String): User
    fun getUser(oAuthUser: OAuthUser): User
}