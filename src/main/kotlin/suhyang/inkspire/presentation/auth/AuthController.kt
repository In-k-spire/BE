package suhyang.inkspire.presentation.auth

import suhyang.inkspire.application.auth.OAuthUri
import com.fasterxml.jackson.databind.SerializerProvider
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
class AuthController(
        private val oAuthUri: OAuthUri
) {

    @GetMapping("/{provider}/uri")
    fun generateUri(
            @PathVariable provider: String,
            @RequestParam redirectUri: String
    ): ResponseEntity<Any> {
        val uri: String = oAuthUri.generate(provider, redirectUri);
        return ResponseEntity.ok(uri);
    }
}