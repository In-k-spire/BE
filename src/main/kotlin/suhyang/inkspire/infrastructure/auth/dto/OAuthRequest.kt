package suhyang.inkspire.infrastructure.auth.dto

import feign.form.FormProperty

data class OAuthRequest(
        val googleToken: GoogleToken,
        val gitHubToken: GitHubToken
) {
    data class GoogleToken(
            @FormProperty("client_id") val clientId: String,
            @FormProperty("client_secret") val clientSecret: String,
            @FormProperty("code") val code: String,
            @FormProperty("grant_type") val grantType: String = "authorization_code",
            @FormProperty("redirect_uri") val redirectUri: String
    )
    data class GitHubToken(
            @FormProperty("client_id") val clientId: String,
            @FormProperty("client_secret") val clientSecret: String,
            @FormProperty("code") val code: String,
    )
}
