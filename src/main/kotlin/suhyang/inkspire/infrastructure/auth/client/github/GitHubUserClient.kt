package suhyang.inkspire.infrastructure.auth.client.github

import feign.HeaderMap
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import suhyang.inkspire.infrastructure.auth.dto.OAuthResponse

@FeignClient(name = "GitHubUserClient", url = "https://api.github.com/user")
interface GitHubUserClient {

    @GetMapping
    fun getUser(@RequestHeader("Authorization") token: String): OAuthResponse.OAuthGitHubUserResponse
}
