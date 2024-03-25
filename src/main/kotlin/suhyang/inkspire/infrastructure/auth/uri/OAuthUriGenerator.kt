package suhyang.inkspire.infrastructure.auth.uri

interface OAuthUriGenerator {
    fun generate(redirectUri: String): String;
}