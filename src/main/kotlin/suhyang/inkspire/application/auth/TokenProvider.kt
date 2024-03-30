package suhyang.inkspire.application.auth

interface TokenProvider {
    fun createAccessToken(payload: String): String;
    fun createRefreshToken(payload: String): String;
    fun getSubject(token: String): String;
}