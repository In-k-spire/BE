package suhyang.inkspire.fixture.auth

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
        val OAUTH_유저_NAME: String = "OAuth_유저_NAME"

        fun JWT_토큰_요청(): AuthRequest.TokenRequest = AuthRequest.TokenRequest(OAUTH_인증_코드, REDIRECT_URI);

        fun JWT_토큰_응답(): AuthResponse.JwtTokenResponse = AuthResponse.JwtTokenResponse(엑세스_토큰, 리프레쉬_토큰);

        fun OAuth_유저(): OAuthUser = OAuthUser(OAUTH_유저_ID, OAUTH_유저_NAME);
    }
}