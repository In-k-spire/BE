package suhyang.inkspire.application.auth

interface TokenProvider {
    fun createAccessToken(subject: String): String;
    fun createRefreshToken(subject: String): String;
    fun getSubject(token: String): String;
}