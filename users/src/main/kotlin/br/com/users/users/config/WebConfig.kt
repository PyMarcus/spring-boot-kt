package br.com.users.users.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig: WebMvcConfigurer {

    @Value("\${cors.originPatterns}")
    private val corsOriginPatters: String = ""

    override fun configureContentNegotiation(configurer: ContentNegotiationConfigurer) {
        configurer.favorParameter(false)
            .ignoreAcceptHeader(false)
            .useRegisteredExtensionsOnly(false)
            .defaultContentType(MediaType.APPLICATION_JSON)
            .mediaType("json", MediaType.APPLICATION_JSON)
            .mediaType("xml", MediaType.APPLICATION_XML)
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        val allowedCors = corsOriginPatters.trim().split(",").map { it.trim() }.toTypedArray()
        println(allowedCors.map { it })
        registry.addMapping("/**")
            .allowedMethods("*")
            .allowedOrigins(*allowedCors)
            .allowCredentials(true)
    }
}