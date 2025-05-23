package suhyang.inkspire.infrastructure.user

import lombok.RequiredArgsConstructor
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import suhyang.inkspire.domain.user.User
import suhyang.inkspire.domain.user.UserRepository
import suhyang.inkspire.infrastructure.auth.dto.OAuthUser
import suhyang.inkspire.infrastructure.auth.exception.UserNotFoundException

@RequiredArgsConstructor
@Component
class UserRepositoryImpl(
        private val userJpaRepository: UserJpaRepository
): UserRepository {

    override fun getUser(id: String): User {
        return userJpaRepository.findByIdOrNull(id) ?: throw UserNotFoundException();
    }

    override fun getUser(oAuthUser: OAuthUser): User {
        return userJpaRepository.findByName(oAuthUser.name) ?: saveUser(oAuthUser)
    }

    private fun saveUser(oAuthUser: OAuthUser): User {
        return userJpaRepository.save(User(id = oAuthUser.id, name = oAuthUser.name));
    }

}