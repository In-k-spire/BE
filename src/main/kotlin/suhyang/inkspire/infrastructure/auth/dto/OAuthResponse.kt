package suhyang.inkspire.infrastructure.auth.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class OAuthResponse(
        val oAuthTokenResponse: OAuthTokenResponse,
        val oAuthGitHubUserResponse: OAuthGitHubUserResponse,
        val oAuthUser: OAuthUser
) {
    data class OAuthTokenResponse(
            @JsonProperty("access_token")
            val accessToken: String
    )
    data class OAuthGitHubUserResponse(
            @JsonProperty("login")
            val login: String,
            @JsonProperty("id")
            val id: Int
    )
    data class OAuthGoogleUserResponse(
            @JsonProperty("id")
            val id: String,
            @JsonProperty("name")
            val name: String
    )


}

