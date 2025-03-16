/*package br.com.users.users.config

import io.swagger.v3.oas.models.OpenAPI
import org.springframework.context.annotation.Configuration
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean

@Configuration
class OpenAPIConfig {

    @Bean
    fun customOpenApi() : OpenAPI{
        return OpenAPI()
            .info(
                Info()
                    .title("Restfull API written in kotlin 2.1.10")
                    .version("v1")
                    .description("User API")
                    .termsOfService("n/a")
                    .license(
                        License()
                            .name("Apache 2.0")
                            .url("www.google.com")
                    )
            )
    }
}*/