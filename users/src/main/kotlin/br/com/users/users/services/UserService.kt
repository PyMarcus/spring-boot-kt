package br.com.users.users.services

import br.com.users.users.data.vo.v1.UserVO
import br.com.users.users.data.vo.v2.UserVO as UserVO2

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

    @Autowired
    private lateinit var dozerMapper: DozerMapper

    private val logger: Logger = Logger.getLogger(UserService::class.java.name)

    fun findAll(): List<UserVO>{
        logger.info("Finding all users")
        return dozerMapper.parseListObject(userRepository.findAll(), UserVO::class.java)
    }

    fun findUserById(id: Long): UserVO?{
        logger.info("Finding user with id: $id")
        val userOptional = userRepository.findById(id)
        return if(userOptional.isPresent){
            val user = userOptional.get()
            dozerMapper.parseObject(user, UserVO::class.java)
        }else{
             null
        }
    }

    fun create(user: UserVO): UserVO{
        logger.info("Creating user ${user}")
        val entity = DozerMapper.parseObject(user, UserModel::class.java)
        userRepository.save(entity)
        return dozerMapper.parseObject(entity, UserVO::class.java)
    }

    fun findAllV2(): List<UserVO2>{
        logger.info("Finding all users")
        return dozerMapper.parseListObject(userRepository.findAll(), UserVO2::class.java)
    }

    fun createV2(user: UserVO2): UserVO2{
        logger.info("Creating user ${user}")
        val entity = dozerMapper.mapVOtoEntity(user)
        userRepository.save(entity)
        return dozerMapper.mapEntityToVO(entity)
    }
}