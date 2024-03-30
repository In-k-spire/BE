package suhyang.inkspire.infrastructure.auth.uri

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component
import suhyang.inkspire.infrastructure.auth.annotation.OAuthProvider
import suhyang.inkspire.infrastructure.auth.annotation.OAuthProviderType
import suhyang.inkspire.common.config.properties.OAuthProperties

@RequiredArgsConstructor
@OAuthProvider(OAuthProviderType.GITHUB)
class GithubUri(
        private val oAuthProperties: OAuthProperties
): OAuthUriGenerator {

    override fun generate(redirectUri: String): String {
        return oAuthProperties.github.endPoint + "?" +
                "client_id=" + oAuthProperties.github.clientId+ "&" +
                "redirect_uri=" + redirectUri;
    }


}