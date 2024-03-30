package suhyang.inkspire.infrastructure.auth.client.google

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import suhyang.inkspire.infrastructure.auth.dto.OAuthResponse

@FeignClient(name = "GoogleUserClient", url = "https://www.googleapis.com/oauth2/v1/userinfo")
interface GoogleUserClient {

    @GetMapping("?alt=json&access_token={TOKEN}")
    fun getUser(@PathVariable("TOKEN") accessToken: String): OAuthResponse.OAuthGoogleUserResponse;
}