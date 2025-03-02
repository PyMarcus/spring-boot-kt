package br.com.users.users.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.*

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException (val date: Date, exception: String?): RuntimeException(exception)