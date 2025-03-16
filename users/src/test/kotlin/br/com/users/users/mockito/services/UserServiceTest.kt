package br.com.users.users.mockito.services

import br.com.users.users.mocks.UserMock
import br.com.users.users.models.UserModel
import br.com.users.users.repository.UserRepository
import br.com.users.users.services.UserService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import kotlin.test.assertNotNull

@ExtendWith(MockitoExtension::class)
class UserServiceTest {

 private lateinit var inputObject: UserMock

 @InjectMocks
 private lateinit var service: UserService

 @Mock
 private lateinit var repository: UserRepository


    @BeforeEach
     fun setUp() {
        this.inputObject = UserMock()
        MockitoAnnotations.openMocks(this)
     }

    @Test
     fun findAll() {
        val users = inputObject.mockUsers()
        val usersModel = users.map {
            UserModel(it.key, it.name)
        }
        `when`(repository.findAll()).thenReturn(usersModel)

        val serviceList = service.findAll()
        assertNotNull(serviceList)
        assertEquals(21, serviceList.size)
    }

    @Test
     fun findUserById() {
       val user = inputObject.mockUser()
       user.key = 25L
        val userModel = UserModel()
        userModel.id = user.key
        userModel.name = user.name
       `when`(repository.findById(25L)).thenReturn(Optional.of(userModel))

       val result = service.findUserById(25L)

       assertNotNull(result)
       assertEquals(result.key, user.key)
     }

    @Test
     fun create() {
         val entity = inputObject.mockUserModel()
         val persisted = entity.copy()
         persisted.id = 0

         val vo = inputObject.mockUserVO()
         `when`(repository.save(entity)).thenReturn(persisted)

        val result = service.create(vo)
        assertNotNull(result)
        assertEquals(result.key, persisted.id)
     }

    @Test
     fun findAllV2() {}

    @Test
     fun createV2() {}
}