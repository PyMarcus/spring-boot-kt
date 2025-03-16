package br.com.users.users.data.vo.v1

import com.github.dozermapper.core.Mapping
import org.springframework.hateoas.RepresentationModel

data class UserVO (
    @Mapping("id")
    var key: Long = 0,
    var name: String = ""
): RepresentationModel<UserVO>()
