package suhyang.inkspire.infrastructure.auth.client.github

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
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Component
@RequiredArgsConstructor
@OAuthProvider(OAuthProviderType.GITHUB)
class GitHubOAuthClient(
        private val oAuthProperties: OAuthProperties,
        private val gitHubTokenClient: GitHubTokenClient,
        private val gitHubUserClient: GitHubUserClient
): OAuthClientManager {
    override fun getOAuthUser(authorizationCode: String, redirectUri: String): OAuthUser {
        val accessToken: String = getToken(authorizationCode);
        return getOauthUser(accessToken);
    }

    private fun getToken(authorizationCode: String): String {
        try {
            val responseData: String = gitHubTokenClient.get(
                    clientId = oAuthProperties.github.clientId,
                    clientSecret = oAuthProperties.github.clientSecret,
                    code = authorizationCode
            );
            return extractAccessToken(responseData);
        } catch(e: RestClientException) {
            throw OAuthException();
        }
    }

    private fun getOauthUser(accessToken: String): OAuthUser {
        val token: String = "Bearer $accessToken";
        val gitOAuthResponse: OAuthResponse.OAuthGitHubUserResponse = gitHubUserClient.getUser(token);
        return OAuthUser(id = gitOAuthResponse.id.toString(), name = gitOAuthResponse.login);
    }

    private fun extractAccessToken(responseData: String): String {
        return responseData.split("&")[0].split("=")[1];
    }
}