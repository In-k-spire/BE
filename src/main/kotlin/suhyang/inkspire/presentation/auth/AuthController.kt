package suhyang.inkspire.presentation.auth

import suhyang.inkspire.application.auth.OAuthUri
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import suhyang.inkspire.application.auth.AuthService
import suhyang.inkspire.application.auth.OAuthClient
import suhyang.inkspire.infrastructure.auth.dto.AuthRequest
import suhyang.inkspire.infrastructure.auth.dto.AuthResponse
import suhyang.inkspire.infrastructure.auth.dto.OAuthUser
import suhyang.inkspire.presentation.common.cookie.CookieManager
import suhyang.inkspire.presentation.common.cookie.JwtTokenCookieGenerator

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
class AuthController(
        private val oAuthUri: OAuthUri,
        private val oAuthClient: OAuthClient,
        private val authService: AuthService,
        private val jwtTokenCookieGenerator: JwtTokenCookieGenerator
) {

    @GetMapping("/{provider}/uri")
    fun generateUri(
            @PathVariable provider: String,
            @RequestParam redirectUri: String,
    ): ResponseEntity<AuthResponse.URIResponse> {
        val uri: String = oAuthUri.generate(provider, redirectUri);
        return ResponseEntity.ok(AuthResponse.URIResponse(uri = uri));
    }

    @PostMapping("/{provider}/token")
    fun generateToken(
            @PathVariable provider: String,
            @RequestBody tokenRequest: AuthRequest.TokenRequest
    ): ResponseEntity<Unit> {
        val oAuthUser: OAuthUser = oAuthClient.get(provider, tokenRequest.authorizationCode, tokenRequest.redirectUri);
        val jwtTokenResponse: AuthResponse.JwtTokenResponse = authService.generateJwtToken(oAuthUser);

        val accessTokenCookie: ResponseCookie = jwtTokenCookieGenerator.generateAccessTokenCookie(jwtTokenResponse.accessToken);

        val refreshTokenCookie: ResponseCookie = jwtTokenCookieGenerator.generateRefreshTokenCookie(jwtTokenResponse.refreshToken);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .build();
    }

    @PatchMapping("/reissue/token")
    fun reissueToken(
            @RequestBody reissueRequest: AuthRequest.ReissueRequest
    ):ResponseEntity<AuthResponse.ReissuedTokenResponse> {
        val reissuedTokenResponse = AuthResponse.ReissuedTokenResponse(
                accessToken =  authService.reIssueAccessToken(reissueRequest.refreshToken)
        );
        return ResponseEntity.ok(reissuedTokenResponse);
    }

}