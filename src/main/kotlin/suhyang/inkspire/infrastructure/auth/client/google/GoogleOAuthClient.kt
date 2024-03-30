package suhyang.inkspire.infrastructure.auth.client.google

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClientException
import suhyang.inkspire.common.config.properties.OAuthProperties
import suhyang.inkspire.infrastructure.auth.annotation.OAuthProvider
import suhyang.inkspire.infrastructure.auth.annotation.OAuthProviderType
import suhyang.inkspire.infrastructure.auth.client.OAuthClientManager
import suhyang.inkspire.infrastructure.auth.dto.OAuthRequest
import suhyang.inkspire.infrastructure.auth.dto.OAuthResponse
import suhyang.inkspire.infrastructure.auth.dto.OAuthUser
import suhyang.inkspire.infrastructure.auth.exception.OAuthException
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Component
@RequiredArgsConstructor
@OAuthProvider(OAuthProviderType.GOOGLE)
class GoogleOAuthClient(
        private val oAuthProperties: OAuthProperties,
        private val googleTokenClient: GoogleTokenClient,
        private val googleUserClient: GoogleUserClient
): OAuthClientManager {
    override fun getOAuthUser(authorizationCode: String, redirectUri: String): OAuthUser {
        val accessToken: String = getToken(authorizationCode, redirectUri).accessToken;
        return getUser(accessToken);
    }

    private fun getToken(authorizationCode: String, redirectUri: String):OAuthResponse.OAuthTokenResponse  {
        val tokenRequest = OAuthRequest.GoogleToken(
                clientId = oAuthProperties.google.clientId,
                clientSecret = oAuthProperties.google.clientSecret,
                code = URLDecoder.decode(authorizationCode, StandardCharsets.UTF_8),
                redirectUri = redirectUri)
        println(tokenRequest.toString());
        try {
            return googleTokenClient.get(tokenRequest);
        } catch(e: RestClientException) {
            throw OAuthException();
        }
    }

    private fun getUser(accessToken: String): OAuthUser {
        try {
            val googleUserResponse: OAuthResponse.OAuthGoogleUserResponse = googleUserClient.getUser(accessToken);
            return OAuthUser(id = googleUserResponse.id, name = googleUserResponse.name);
        } catch(e: RestClientException) {
            throw OAuthException();
        }
    }
}