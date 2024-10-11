package suhyang.inkspire.presentation.common.config

import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import suhyang.inkspire.presentation.common.principal.AuthenticationPrincipalArgumentResolver

@RequiredArgsConstructor
@Configuration
class WebConfig(
        private val authenticationPrincipalArgumentResolver: AuthenticationPrincipalArgumentResolver
): WebMvcConfigurer {

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(authenticationPrincipalArgumentResolver);
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
                .allowedOrigins("https://ink-spire.netlify.app/", "http://localhost:3000")
                .allowedMethods("*");
    }
}
