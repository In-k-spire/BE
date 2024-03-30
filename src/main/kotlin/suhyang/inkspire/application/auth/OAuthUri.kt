package suhyang.inkspire.application.auth

import suhyang.inkspire.infrastructure.auth.uri.OAuthUriGenerator
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component
import suhyang.inkspire.infrastructure.auth.annotation.OAuthProviderRouter

@Component
@RequiredArgsConstructor
class OAuthUri(
        private val oAuthUriGenerators: List<OAuthUriGenerator>
) {
    fun generate(provider: String, redirectUri: String): String {
        val generator: OAuthUriGenerator = OAuthProviderRouter.route(oAuthUriGenerators, provider);
        return generator.generate(redirectUri);
    }
}