package suhyang.inkspire.infrastructure.auth.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class OAuthUser(
        @JsonProperty("id")
        val id: String,
        @JsonProperty("name")
        val name: String
)
