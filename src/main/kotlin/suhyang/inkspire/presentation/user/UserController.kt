package suhyang.inkspire.presentation.user

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import suhyang.inkspire.domain.user.User
import suhyang.inkspire.infrastructure.user.dto.UserResponseDto
import suhyang.inkspire.presentation.common.principal.AuthenticationPrincipal

@RequestMapping("/api/user")
@RestController
class UserController {

    @GetMapping
    fun getUser(
            @AuthenticationPrincipal loginUser: User
    ): ResponseEntity<UserResponseDto.UserResponse> {
        return ResponseEntity.ok(UserResponseDto.UserResponse(loginUser));
    }

}