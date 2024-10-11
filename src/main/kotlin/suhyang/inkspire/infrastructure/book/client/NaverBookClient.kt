package suhyang.inkspire.infrastructure.book.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import suhyang.inkspire.infrastructure.book.dto.BookResponseDto
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@FeignClient(name = "NaverBookClient", url = "https://openapi.naver.com/v1/search/book.json")
interface NaverBookClient {
    @GetMapping
    fun search(
            @RequestHeader("X-Naver-Client-Id") cliendId: String,
            @RequestHeader("X-Naver-Client-Secret") clientSercret: String,
            @RequestParam("query") query: String,
            @RequestParam("display") display: Int
    ): BookResponseDto.NaverClientResponse;
}