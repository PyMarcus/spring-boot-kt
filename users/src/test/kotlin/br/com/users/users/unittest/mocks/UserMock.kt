package br.com.users.users.mocks

import br.com.users.users.data.vo.v1.UserVO
import br.com.users.users.models.UserModel

class UserMock {
    fun mockUser(): UserVO{
        val user: UserVO = UserVO(0, "mocked user")
        return user
    }

    fun mockUserVO(): UserVO{
        val user: UserVO = UserVO(0, "mocked user")
        return user
    }

    fun mockUserModel(): UserModel{
        val user: UserModel = UserModel(0, "mocked user")
        return user
    }

    fun mockUsers(): List<UserVO>{
        val users: MutableList<UserVO> = ArrayList()
        for(i in 15..35){
            val user: UserVO = UserVO(i.toLong(), "mocked user $i")
            users.add(user)
        }
        return users
    }
}