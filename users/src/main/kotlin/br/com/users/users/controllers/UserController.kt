package br.com.users.users.controllers

import br.com.users.users.models.UserModel
import br.com.users.users.dto.UserResponseDTO
import br.com.users.users.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findall(): List<UserResponseDTO>{
        return userService.findAll()
    }

    @GetMapping("/{id}",
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findUserById(@PathVariable id: Long): ResponseEntity<UserResponseDTO>{
        val user = userService.findUserById(id)
        return if(user != null){
            ResponseEntity.ok(user)
        }else{
            ResponseEntity.noContent().build()
        }
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody user: UserModel): UserResponseDTO{
        return userService.create(user)
    }
}