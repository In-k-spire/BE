package suhyang.inkspire.fixture.auth

import org.springframework.http.ResponseCookie
import suhyang.inkspire.infrastructure.auth.dto.AuthRequest
import suhyang.inkspire.infrastructure.auth.dto.AuthResponse
import suhyang.inkspire.infrastructure.auth.dto.OAuthUser

class AuthFixturesAndDocs {
    companion object {
        val GOOGLE_PROVIDER: String = "google";
        val GITHUB_PROVIDER: String = "github";
        val OAUTH_로그인_링크: String = "https://"
        val REDIRECT_URI: String = "inkspire.kro.kr";
        val OAUTH_인증_코드: String = "authorization code";
        val 엑세스_토큰: String = "accessToken";
        val 리프레쉬_토큰: String = "refreshToken";
        val OAUTH_유저_ID: String = "OAuth_유저_ID";
        val OAUTH_유저_NAME: String = "OAuth_유저_NAME";
        val COOKIE_NAME: String = "COOKIE_NAME";
        val COOKIE_VALUE: String = "COOKIE_VALUE"
        val MAX_AGE: Long = 12000000;

        fun JWT_토큰_요청(): AuthRequest.TokenRequest = AuthRequest.TokenRequest(OAUTH_인증_코드, REDIRECT_URI);

        fun JWT_토큰_응답(): AuthResponse.JwtTokenResponse = AuthResponse.JwtTokenResponse(엑세스_토큰, 리프레쉬_토큰);

        fun OAuth_유저(): OAuthUser = OAuthUser(OAUTH_유저_ID, OAUTH_유저_NAME);

        fun 엑세스_토큰_재발급_요청(): AuthRequest.ReissueRequest = AuthRequest.ReissueRequest(리프레쉬_토큰);

        fun RESPONSE_COOKIE_응답(): ResponseCookie
        = ResponseCookie.from(COOKIE_NAME, COOKIE_VALUE)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(MAX_AGE)
                .build()
    }
}