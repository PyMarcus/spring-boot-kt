package br.com.users.users.data.vo.v2

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.util.*

@JsonPropertyOrder("id", "date", "name")
class UserVO (
    var id: Long = 0,

    @field:JsonProperty("username")
    var name: String = "",

    var date: Date? = null
)