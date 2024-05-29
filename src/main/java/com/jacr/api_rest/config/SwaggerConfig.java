package com.jacr.api_rest.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 *
 * Agregamos la anotación @OpenApiDefinition para configurar el swagger
 */
@OpenAPIDefinition(
        info = @Info(
                title = "API LIBRARY",
                description = "library api as my first rest api with Spring Boot",
                contact = @Contact(
                        name = "Juan Castañeda",
                        url = "https://github.com/Juan2850",
                        email = "castaneda2850@gmail.com"
                ),
                version = "1.0.0"

        ),
        servers = {
                @Server(
                        description = "DEV SERVER",
                        url = "http://localhost:8080"
                )
        }
)
public class SwaggerConfig {
}
