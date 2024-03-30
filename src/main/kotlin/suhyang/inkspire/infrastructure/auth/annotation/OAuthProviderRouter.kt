package suhyang.inkspire.infrastructure.auth.annotation

import suhyang.inkspire.infrastructure.auth.exception.ProviderNotFoundException
import java.util.function.Supplier

class OAuthProviderRouter {
    companion object {
        fun <T> route(list: List<T>, provider: String?): T {
            return list.stream()
                    .filter { it: T -> it!!::class.java.isAnnotationPresent(OAuthProvider::class.java) }
                    .filter { it: T -> isSameOAuthProvider(it!!::class.java, provider!!)!! }
                    .findFirst()
                    .orElseThrow<RuntimeException>(Supplier<RuntimeException> { ProviderNotFoundException() })
        }

        private fun isSameOAuthProvider(providerClass: Class<*>, provider: String): Boolean? {
            val providerType = providerClass.getAnnotation(OAuthProvider::class.java)?.value;
            return providerType?.isSameProvider(provider);
        }
    }



}