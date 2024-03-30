package suhyang.inkspire.application.auth

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component
import suhyang.inkspire.infrastructure.auth.annotation.OAuthProviderRouter
import suhyang.inkspire.infrastructure.auth.client.OAuthClientManager
import suhyang.inkspire.infrastructure.auth.dto.OAuthUser

@Component
@RequiredArgsConstructor
class OAuthClient(
        private val oAuthClientManagers: List<OAuthClientManager>
) {
    fun get(provider: String, authorizationCode: String, redirectUri: String): OAuthUser {
        val client: OAuthClientManager = OAuthProviderRouter.route(oAuthClientManagers, provider);
        return client.getOAuthUser(authorizationCode, redirectUri);
    }
}