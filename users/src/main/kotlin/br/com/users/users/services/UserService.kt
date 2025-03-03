package br.com.users.users.services

import br.com.users.users.data.vo.v1.UserVO
import br.com.users.users.mapper.DozerMapper
import br.com.users.users.models.UserModel
import br.com.users.users.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    private val logger: Logger = Logger.getLogger(UserService::class.java.name)

    fun findAll(): List<UserVO>{
        logger.info("Finding all users")
        return DozerMapper.parseListObject(userRepository.findAll(), UserVO::class.java)
    }

    fun findUserById(id: Long): UserVO?{
        logger.info("Finding user with id: $id")
        val userOptional = userRepository.findById(id)
        return if(userOptional.isPresent){
            val user = userOptional.get()
             DozerMapper.parseObject(user, UserVO::class.java)
        }else{
             null
        }
    }

    fun create(user: UserVO): UserVO{
        logger.info("Creating user ${user}")
        val entity = DozerMapper.parseObject(user, UserModel::class.java)
        userRepository.save(entity)
        return DozerMapper.parseObject(user, UserVO::class.java)
    }
}