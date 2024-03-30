package suhyang.inkspire.infrastructure.auth.client.google

import feign.FeignException
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import suhyang.inkspire.infrastructure.auth.dto.AuthRequest
import suhyang.inkspire.infrastructure.auth.dto.OAuthRequest
import suhyang.inkspire.infrastructure.auth.dto.OAuthResponse


@FeignClient(name = "GoogleTokenClient", url = "https://oauth2.googleapis.com/token")
interface GoogleTokenClient {

    @PostMapping
    @Throws(FeignException::class)
    fun get(request: OAuthRequest.GoogleToken):OAuthResponse.OAuthTokenResponse
}