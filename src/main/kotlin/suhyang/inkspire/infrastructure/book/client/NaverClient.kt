package suhyang.inkspire.infrastructure.book.client

import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClientException
import suhyang.inkspire.infrastructure.book.dto.BookResponseDto

@RequiredArgsConstructor
@Component
class NaverClient(
        private val naverBookClient: NaverBookClient,
        @Value("\${naver.client-id}") val clientId: String,
        @Value("\${naver.client-secret}") val clientSecret: String
) {


    fun search(query: String, display: Int): List<BookResponseDto.NaverBookResponse> {
        return getResult(query, display);
    }

    private fun getResult(query: String, display: Int): List<BookResponseDto.NaverBookResponse> {
        try {
            return naverBookClient.search(
                    cliendId = clientId,
                    clientSercret = clientSecret,
                    query = query,
                    display = display
            ).naverBookResponseList;
        } catch (e: RestClientException) {
            throw IllegalArgumentException("client error");
        }
    }
}