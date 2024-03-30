package suhyang.inkspire.infrastructure.auth.uri

import org.springframework.stereotype.Component
import suhyang.inkspire.infrastructure.auth.annotation.OAuthProvider
import suhyang.inkspire.infrastructure.auth.annotation.OAuthProviderType
import suhyang.inkspire.common.config.properties.OAuthProperties

@OAuthProvider(OAuthProviderType.GOOGLE)
class GoogleUri(
        private val oAuthProperties: OAuthProperties
): OAuthUriGenerator {
    override fun generate(redirectUri: String): String {
        return oAuthProperties.google.endPoint + "?" +
                "client_id=" + oAuthProperties.google.clientId + "&" +
                "redirect_uri=" + redirectUri + "&" +
                "response_type=code&" + "scope=" + oAuthProperties.google.scopes.joinToString(" ");
    }

}