package suhyang.inkspire.infrastructure.auth.client

import suhyang.inkspire.infrastructure.auth.dto.OAuthUser

interface OAuthClientManager {
    fun getOAuthUser(authorizationCode: String, redirectUri: String): OAuthUser;
}