package br.com.users.users.services

import br.com.users.users.controllers.UserController
import br.com.users.users.data.vo.v1.UserVO
import br.com.users.users.data.vo.v2.UserVO as UserVO2

import br.com.users.users.mapper.DozerMapper
import br.com.users.users.models.UserModel
import br.com.users.users.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    private val dozerMapper: DozerMapper = DozerMapper

    private val logger: Logger = Logger.getLogger(UserService::class.java.name)

    fun findAll(): List<UserVO>{
        logger.info("Finding all users")
        val usersVO =  dozerMapper.parseListObject(userRepository.findAll(), UserVO::class.java)
        for(user in usersVO){
            val withSelfRel = linkTo(UserController::class.java).slash(user.key).withSelfRel()
            user.add(withSelfRel)
        }
        return usersVO
    }

    fun findUserById(id: Long): UserVO?{
        logger.info("Finding user with id: $id")
        val userOptional = userRepository.findById(id)
        return if(userOptional.isPresent){
            val user = userOptional.get()
            val userVO: UserVO = dozerMapper.parseObject(user, UserVO::class.java)
            // link hateos
            val withSelfRel = linkTo(UserController::class.java).slash(userVO.key).withSelfRel()
            userVO.add(withSelfRel)
            userVO
        }else{
             null
        }
    }

    fun create(user: UserVO): UserVO{
        logger.info("Creating user ${user}")
        val entity = DozerMapper.parseObject(user, UserModel::class.java)
        userRepository.save(entity)
        val userVO: UserVO = dozerMapper.parseObject(entity, UserVO::class.java)
        // link hateos
        val withSelfRel = linkTo(UserController::class.java).slash(userVO.key).withSelfRel()
        userVO.add(withSelfRel)
        return userVO
    }

    fun findAllV2(): List<UserVO2>{
        logger.info("Finding all users")
        val usersVO =  dozerMapper.parseListObject(userRepository.findAll(), UserVO2::class.java)
        return usersVO
    }

    fun createV2(user: UserVO2): UserVO2{
        logger.info("Creating user ${user}")
        val entity = dozerMapper.mapVOtoEntity(user)
        userRepository.save(entity)
        return dozerMapper.mapEntityToVO(entity)
    }
}