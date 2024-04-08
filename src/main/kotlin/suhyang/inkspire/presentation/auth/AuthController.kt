package suhyang.inkspire.presentation.auth

import suhyang.inkspire.application.auth.OAuthUri
import com.fasterxml.jackson.databind.SerializerProvider
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import suhyang.inkspire.application.auth.AuthService
import suhyang.inkspire.application.auth.OAuthClient
import suhyang.inkspire.domain.user.User
import suhyang.inkspire.infrastructure.auth.dto.AuthRequest
import suhyang.inkspire.infrastructure.auth.dto.AuthResponse
import suhyang.inkspire.infrastructure.auth.dto.OAuthUser
import suhyang.inkspire.presentation.common.principal.AuthenticationPrincipal

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
class AuthController(
        private val oAuthUri: OAuthUri,
        private val oAuthClient: OAuthClient,
        private val authService: AuthService
) {

    @GetMapping("/{provider}/uri")
    fun generateUri(
            @PathVariable provider: String,
            @RequestParam redirectUri: String
    ): ResponseEntity<AuthResponse.URIResponse> {
        val uri: String = oAuthUri.generate(provider, redirectUri);
        return ResponseEntity.ok(AuthResponse.URIResponse(uri = uri));
    }

    @PostMapping("/{provider}/token")
    fun generateToken(
            @PathVariable provider: String,
            @RequestBody tokenRequest: AuthRequest.TokenRequest
    ): ResponseEntity<AuthResponse.JwtTokenResponse> {
        val oAuthUser: OAuthUser = oAuthClient.get(provider, tokenRequest.authorizationCode, tokenRequest.redirectUri);
        return ResponseEntity.ok(authService.generateJwtToken(oAuthUser));
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