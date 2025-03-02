package br.com.users.users.exceptions.handlers

import br.com.users.users.exceptions.ExceptionResponse
import br.com.users.users.exceptions.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception
import java.util.Date

@ControllerAdvice
@RestController
class CustomizedExceptionResponseHandler : ResponseEntityExceptionHandler(){

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(exception: Exception, request: WebRequest): ResponseEntity<ExceptionResponse>{
        val erro = ExceptionResponse(
            Date(),
            exception.message,
            request.getDescription(false)
        )

        return ResponseEntity<ExceptionResponse>(erro, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundExceptions(exception: Exception, request: WebRequest): ResponseEntity<NotFoundException>{
        val erro = NotFoundException(
            Date(),
            request.getDescription(false)
        )
        return ResponseEntity<NotFoundException>(erro, HttpStatus.NOT_FOUND)
    }
}