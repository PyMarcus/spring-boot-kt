package br.com.users.users.services

import br.com.users.users.models.UserModel
import br.com.users.users.dto.UserResponseDTO
import br.com.users.users.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class UserService {

    private lateinit var userRepository: UserRepository
    private val logger: Logger = Logger.getLogger(UserService::class.java.name)

    fun findAll(): List<UserResponseDTO>{
        logger.info("Finding all users")
        return userRepository.findAll().map { UserResponseDTO(it.name) }
    }

    fun findUserById(id: Long): UserResponseDTO?{
        logger.info("Finding user with id: $id")
        val userOptional = userRepository.findById(id)
        return if(userOptional.isPresent){
            val user = userOptional.get()
             UserResponseDTO(user.name)
        }else{
             null
        }
    }

    fun create(user: UserModel): UserResponseDTO{
        logger.info("Creating user ${user}")
        userRepository.save(user)
        return UserResponseDTO(user.name)
    }
}