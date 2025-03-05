package br.com.users.users.controllers

import br.com.users.users.data.vo.v1.UserVO
import br.com.users.users.data.vo.v2.UserVO as UserVO2
import br.com.users.users.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    private lateinit var userService: UserService


    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun findall(): List<UserVO>{
        return userService.findAll()
    }

    @GetMapping("/{id}",
        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun findUserById(@PathVariable id: Long): ResponseEntity<UserVO>{
        val user = userService.findUserById(id)
        return if(user != null){
            ResponseEntity.ok(user)
        }else{
            ResponseEntity.noContent().build()
        }
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody user: UserVO): UserVO{
        return userService.create(user)
    }

    @GetMapping(
        value = ["/v2"],
        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun findallV2(): List<UserVO2>{
        return userService.findAllV2()
    }


    @PostMapping(
        value = ["/v2"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun createV2(@RequestBody user: UserVO2): UserVO2{
        return userService.createV2(user)
    }
}