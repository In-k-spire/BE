package suhyang.inkspire.infrastructure.auth.client.github

import feign.FeignException
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import suhyang.inkspire.infrastructure.auth.dto.OAuthRequest
import suhyang.inkspire.infrastructure.auth.dto.OAuthResponse

@FeignClient(name = "GitHubTokenClient", url = "https://github.com/login/oauth/access_token")
interface GitHubTokenClient {

    @PostMapping
    @Throws(FeignException::class)
    fun get(@RequestParam("client_id") clientId: String,
            @RequestParam("client_secret") clientSecret: String,
            @RequestParam("code") code: String
    ): String
}