package suhyang.inkspire.infrastructure.auth.annotation

import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor

@AllArgsConstructor
enum class OAuthProviderType(
        private val provider: String
) {
    GOOGLE("google"),
    GITHUB("github");

    fun isSameProvider(provider: String): Boolean {
        return this.provider == provider;
    }

}